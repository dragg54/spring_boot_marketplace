package ecommerce.dtos.responses;

import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class ProductResponse {
    private Long productId;
    private ProductCategory productCategory;
    private User productOwner;
    private String productName;
    private String description;
    private Long price;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
}
