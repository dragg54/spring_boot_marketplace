package ecommerce.dtos.requests;

import ecommerce.entities.ProductCategory;
import ecommerce.entities.ProductImage;
import ecommerce.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
public class PostProductRequest {
    private Long productCategoryId;
    private String productName;
    private List<MultipartFile> productImages;
    private String description;
    private Long price;
    private Integer quantity;
}
