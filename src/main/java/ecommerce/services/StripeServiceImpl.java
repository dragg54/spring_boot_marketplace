package ecommerce.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PriceCollection;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.model.Price;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import ecommerce.constants.StripeProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
public class StripeServiceImpl implements StripeService {
    private final Logger LOGGER = LoggerFactory.getLogger(StripeServiceImpl.class);
    private StripeProduct product;
    public String createStripePrice() {
        PriceCreateParams params = PriceCreateParams.builder()
                .setUnitAmount(product.getPrice())
                .setCurrency("usd")
                .setProductData(
                        PriceCreateParams.ProductData.builder()
                                .setId(String.valueOf(product.getProductId()))
                                .build()
                )
                .build();

        try {
            Price price = Price.create(params);
            LOGGER.info("Price ID: " + price.getId());
            return price.getId();
        } catch (StripeException e) {
            LOGGER.error("Stripe price creation failed");
            e.printStackTrace();
        }
        return null;
    }

    public Session createStripeCheckout(String priceId) throws StripeException {
        String YOUR_DOMAIN = "http://localhost:4242";
        StripeProduct stripeProduct;
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "?success=true")
                        .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setPrice(priceId)
                                        .build())
                        .build();
        return Session.create(params);
    }
}
