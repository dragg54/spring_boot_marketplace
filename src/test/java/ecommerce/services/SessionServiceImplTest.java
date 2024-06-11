package ecommerce.services;

import com.stripe.exception.ApiException;
import com.stripe.exception.StripeException;
import ecommerce.constants.StripeProduct;
import ecommerce.dtos.mappers.SessionMapper;
import ecommerce.dtos.mappers.StripeMapper;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Session;
import ecommerce.entities.User;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import ecommerce.repositories.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class SessionServiceImplTest {
    @Mock
    private BidRepository bidRepository;

    @Mock
    private UserDtlServiceImpl userDtlService;

    @Mock
    private SessionMapper sessionMapper;

    @Mock
    private StripeMapper stripeMapper;

    @Mock
    private StripeServiceImpl stripeService;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionServiceImpl sessionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    private OpenSessionRequest openSessionRequest;
    private CheckoutSessionRequest checkoutSessionRequest;
    private Bid bid;
    private User user;
    private Session session;
    private StripeProduct stripeProduct;

    @BeforeEach
    void setUp() {
        openSessionRequest = new OpenSessionRequest();
        openSessionRequest.setBidId(1L);

        bid = new Bid();
        bid.setBidId(1L);

        user = new User();
        user.setUserId(1L);

        session = new Session();
        session.setSessionId(1L);

        stripeProduct = new StripeProduct();

        checkoutSessionRequest = CheckoutSessionRequest
                .builder()
                .BidId(1L)
                .build();
    }

    @Test
    void testOpenSession() throws NotFoundException, InvalidRequestException {
        when(bidRepository.findById(1L)).thenReturn(Optional.of(bid));
        when(userDtlService.getCurrentUser()).thenReturn(user);
        when(sessionMapper.openSessionRequestToSession(openSessionRequest, user)).thenReturn(session);
        when(stripeMapper.bidToStripeProduct(bid)).thenReturn(stripeProduct);
        when(stripeService.createStripePrice(stripeProduct)).thenReturn("price_123");

        sessionService.openSession(openSessionRequest);

        verify(bidRepository, times(1)).findById(1L);
        verify(userDtlService, times(1)).getCurrentUser();
        verify(sessionMapper, times(1)).openSessionRequestToSession(openSessionRequest, user);
        verify(stripeMapper, times(1)).bidToStripeProduct(bid);
        verify(stripeService, times(1)).createStripePrice(stripeProduct);
        verify(sessionRepository, times(1)).save(session);

        assertEquals("price_123", session.getStripePriceId());
    }

    @Test
    void testCheckoutSessionNotFound() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            sessionService.checkoutSession(checkoutSessionRequest, 1L);
        });

        assertEquals("Session with id 1 not found", exception.getMessage());
        verify(sessionRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(userDtlService, sessionMapper, stripeService, sessionRepository);
    }

    @Test
    void testCheckoutSessionStripeException() throws StripeException, InstantiationException, IllegalAccessException {
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(userDtlService.getCurrentUser()).thenReturn(user);
        when(sessionMapper.checkoutSessionRequestToSession(checkoutSessionRequest, user)).thenReturn(session);
        when(stripeService.createStripeCheckout(anyString()))
                .thenThrow(new ApiException("Stripe error", "1000", "5000", 500, null));
        doThrow(new ApiException("Stripe error", "1000", "5000", 500, null))
                .when(stripeService).createStripeCheckout(anyString());

        StripeException exception = assertThrows(StripeException.class, () -> {
            sessionService.checkoutSession(checkoutSessionRequest, 1L);
        });

        assertEquals("Stripe error", exception.getMessage());
        verify(sessionRepository, times(1)).findById(1L);
        verify(userDtlService, times(1)).getCurrentUser();
        verify(sessionMapper, times(1)).checkoutSessionRequestToSession(checkoutSessionRequest, user);
        verify(stripeService, times(1)).createStripeCheckout(session.getStripePriceId());
        verifyNoMoreInteractions(sessionRepository);
    }

    @Test
    void getOpenSessionProductPriceById() {
    }
}