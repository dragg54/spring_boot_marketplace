package ecommerce.dtos.responses;

import ecommerce.entities.ProductCategory;
import ecommerce.entities.ProductImage;
import ecommerce.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class ProductResponse {
    private Long productId;
    private ProductCategory productCategory;
    private User productOwner;
    private String productName;
    private String description;
    private List<ProductImage> productImages;
    private Long price;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
}
