package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import ecommerce.constants.BidStatus;
import ecommerce.constants.SessionStatus;
import ecommerce.constants.StripeProduct;
import ecommerce.dtos.mappers.SessionMapper;
import ecommerce.dtos.mappers.StripeMapper;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Session;
import ecommerce.entities.User;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import ecommerce.repositories.SessionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService{
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserDtlServiceImpl userDtlService;
    private final BidRepository bidRepository;
    private final StripeServiceImpl stripeService;
    private final StripeMapper stripeMapper;
    private final EntityManager entityManager;
    private final Logger LOGGER = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Transactional(rollbackOn = {StripeException.class,
            InvalidRequestException.class, RuntimeException.class, StripeException.class})
    public void openSession(OpenSessionRequest request) throws NotFoundException,
            InvalidRequestException, StripeException, DuplicateException {
        Bid existingBid = bidRepository.findById(request.getBidId())
                .orElseThrow(()->
                        new NotFoundException(String.format("Bid with id %d not found", request.getBidId())));
        if(existingBid.getBidStatus() != BidStatus.ACCEPTED){
            String errMsg = "Bid has to be in accepted state before session can be created";
            LOGGER.error(String.format(errMsg));
            throw new InvalidRequestException(errMsg);
        }
        User user = userDtlService.getCurrentUser();
        Optional<Session> existingSession = sessionRepository.findByBid(existingBid);
        if(existingSession.isPresent()&& existingSession.get().getUser().getUserId() == user.getUserId()
            ){
            LOGGER.error(String.format("Duplicate open session for bid %d and user %d",
                    existingBid.getBidId(), user.getUserId()));
            throw new DuplicateException(String.format("Duplicate open session for bid %d and user %d",
                    existingBid.getBidId(), user.getUserId()));
        }
        User managedUser = entityManager.merge(user);
        Session session = sessionMapper.openSessionRequestToSession(request, managedUser, existingBid);
        StripeProduct stripeProduct = stripeMapper.bidToStripeProduct(existingBid);
        String stripePriceId = stripeService.createStripePrice(stripeProduct);
        session.setStripePriceId(stripePriceId);
        LOGGER.info("Session opened");
        sessionRepository.save(session);
    }

    @Transactional(rollbackOn = {StripeException.class,
            InvalidRequestException.class, RuntimeException.class, StripeException.class})
    public void checkoutSession(CheckoutSessionRequest request, Long sessionId)
            throws NotFoundException, StripeException, DuplicateException {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(()->new NotFoundException(String.format("Session with id %d not found", sessionId)));
        if(session.getStatus() == SessionStatus.CHECKED_OUT){
            LOGGER.error(String.format("Duplicate checkout session for bid %d", sessionId));
            throw new DuplicateException(String.format("Duplicate checkout session for bid %d",
                    sessionId));
        }
        User user = userDtlService.getCurrentUser();
        Session updatedSession = sessionMapper.checkoutSessionRequestToSession(request, user, session);
        stripeService.createStripeCheckout(session.getStripePriceId());
        sessionRepository.save(updatedSession);
    }

    public Price getOpenSessionProductPriceById(String stripeProductId) throws StripeException {
       Price stripeProductPrice = Price.retrieve(stripeProductId);
       return stripeProductPrice;
    }
}
