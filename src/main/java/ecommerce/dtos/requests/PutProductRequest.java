package ecommerce.dtos.requests;

import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PutProductRequest {
    private ProductCategory productCategory;
    private User productOwner;
    private String productName;
    private String description;
    private Long price;
    private Integer quantity;
}
