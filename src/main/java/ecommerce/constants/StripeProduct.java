package ecommerce.constants;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StripeProduct {
    private Long productId;
    private Long price;
}
