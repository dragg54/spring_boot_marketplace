package ecommerce.services;

import ecommerce.entities.ProductCategory;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final ProductCategoryRepository productCategoryRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    public Optional<ProductCategory> getProductCategory(Long id) throws NotFoundException {
           Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
           if(!productCategory.isPresent()){
               LOGGER.error(String.format("Product category with id %d found not found", id));
               throw new NotFoundException();
           }
           LOGGER.info(String.format("Product category with id %d found", id));
           return productCategory;
    }

    public List<ProductCategory> getProductCategories(){
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> getProductCategoryByName(String categoryName){
        return Optional.ofNullable(productCategoryRepository.findByCategoryName(categoryName));
    }
}
