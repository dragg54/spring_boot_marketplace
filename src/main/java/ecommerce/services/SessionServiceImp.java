package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import ecommerce.constants.StripeProduct;
import ecommerce.dtos.mappers.SessionMapper;
import ecommerce.dtos.mappers.StripeMapper;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Session;
import ecommerce.entities.User;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import ecommerce.repositories.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionServiceImp implements SessionService{
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserDtlServiceImpl userDtlService;
    private final BidRepository bidRepository;
    private final StripeServiceImpl stripeService;
    private final StripeMapper stripeMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(SessionServiceImp.class);

    @Transactional
    public void openSession(OpenSessionRequest request) throws NotFoundException {
        Bid existingBid = bidRepository.findById(request.getBidId())
                .orElseThrow(()->
                        new NotFoundException(String.format("Bid with id %d not found", request.getBidId())));
        User user = userDtlService.getCurrentUser();
        Session session = sessionMapper.openSessionRequestToSession(request, user);
        StripeProduct stripeProduct = stripeMapper.bidToStripeProduct(existingBid);
        String stripePriceId = stripeService.createStripePrice(stripeProduct);
        session.setStripePriceId(stripePriceId);
        LOGGER.info("Session opened");
        sessionRepository.save(session);
    }

    public void checkoutSession(CheckoutSessionRequest request, Long sessionId)
            throws NotFoundException, StripeException {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(()->new NotFoundException(String.format("Session with id %id not found", sessionId)));
        User user = userDtlService.getCurrentUser();
        Session updatedSession = sessionMapper.checkoutSessionRequestToSession(request, user);
        stripeService.createStripeCheckout(session.getStripePriceId());
        sessionRepository.save(updatedSession);
    }

    public Price getOpenSessionProductPriceById(String stripeProductId) throws StripeException {
       Price stripeProductPrice = Price.retrieve(stripeProductId);
       return stripeProductPrice;
    }
}
