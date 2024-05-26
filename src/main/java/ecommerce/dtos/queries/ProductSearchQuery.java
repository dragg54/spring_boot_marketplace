package ecommerce.dtos.queries;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductSearchQuery {
    private String productName;
    private Long productCategoryId;
    private Long productOwnerId;
}
