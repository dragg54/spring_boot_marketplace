package ecommerce.dtos.requests;

import ecommerce.data.enums.BidStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutBidRequest {
    private Long productId;
    private Long productBidderId;
    private BidStatus bidStatus;
    private Integer quantity;
    private Integer bidPrice;
}
