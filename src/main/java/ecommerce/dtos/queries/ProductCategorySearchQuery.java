package ecommerce.dtos.queries;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductCategorySearchQuery {
    public String productCategoryName;
}
