package ecommerce.dtos.requests;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    private Long bidId;
}
