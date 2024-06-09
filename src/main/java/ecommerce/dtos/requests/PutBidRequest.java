package ecommerce.dtos.requests;

import ecommerce.constants.BidStatus;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutBidRequest {
    private Long productId;
    private Long productBidderId;
    private BidStatus bidStatus;
    private Integer quantity;
    private Integer bidPrice;
}
