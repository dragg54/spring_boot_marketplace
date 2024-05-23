package ecommerce.services;

import ecommerce.entities.ProductCategory;
import ecommerce.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    Optional<ProductCategory> getProductCategory(Long id) throws Exception, NotFoundException;
    List<ProductCategory> getProductCategories();
}
