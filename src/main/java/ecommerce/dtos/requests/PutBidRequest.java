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
    private Long bidId;

    private BidStatus bidStatus;

    private Integer biddingPrice;
}
