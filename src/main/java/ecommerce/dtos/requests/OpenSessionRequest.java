package ecommerce.dtos.requests;

import ecommerce.constants.ShippingDetail;
import ecommerce.entities.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenSessionRequest {
    private Long bidId;
    private ShippingDetail shippingDetail;
}
