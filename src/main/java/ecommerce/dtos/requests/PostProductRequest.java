package ecommerce.dtos.requests;

import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostProductRequest {
    private Long productCategoryId;
    private Long productOwnerId;
    private String productName;
    private String description;
    private Long price;
    private Integer quantity;
}
