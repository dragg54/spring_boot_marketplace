package ecommerce.dtos.queries;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BidSearchQuery {
    private Long productId;
    private String bidStatus;
}
