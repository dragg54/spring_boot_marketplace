package ecommerce.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.checkout.Session;

public interface StripeService {
    String createStripePrice();
    Session createStripeCheckout(String stripePriceId) throws StripeException;
}
