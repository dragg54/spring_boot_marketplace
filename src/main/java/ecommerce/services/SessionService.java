package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.exceptions.NotFoundException;

public interface SessionService {
    void openSession(OpenSessionRequest request) throws NotFoundException;
    void checkoutSession(CheckoutSessionRequest request, Long sessionId) throws NotFoundException, StripeException;

    Price getOpenSessionProductPriceById(String stripeProductId) throws StripeException;
}
