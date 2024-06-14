package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;

public interface SessionService {
    void openSession(OpenSessionRequest request) throws NotFoundException, InvalidRequestException, StripeException, DuplicateException;
    void checkoutSession(CheckoutSessionRequest request, Long sessionId) throws NotFoundException,
            InvalidRequestException, StripeException, DuplicateException;

    Price getOpenSessionProductPriceById(String stripeProductId) throws StripeException;
}
