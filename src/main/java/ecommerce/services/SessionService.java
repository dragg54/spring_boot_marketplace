package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;

public interface SessionService {
    void openSession(OpenSessionRequest request) throws NotFoundException, InvalidRequestException, StripeException;
    void checkoutSession(CheckoutSessionRequest request, Long sessionId) throws NotFoundException,
            InvalidRequestException, StripeException;

    Price getOpenSessionProductPriceById(String stripeProductId) throws StripeException;
}
