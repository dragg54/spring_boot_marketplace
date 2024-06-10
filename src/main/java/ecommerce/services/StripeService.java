package ecommerce.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.checkout.Session;
import ecommerce.constants.StripeProduct;

public interface StripeService {
    String createStripePrice(StripeProduct product);
    Session createStripeCheckout(String stripePriceId) throws StripeException;
}
