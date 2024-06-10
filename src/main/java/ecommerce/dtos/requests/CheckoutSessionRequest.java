package ecommerce.dtos.requests;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutSessionRequest {
    private Long BidId;
}
