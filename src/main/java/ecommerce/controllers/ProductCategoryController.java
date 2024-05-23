package ecommerce.controllers;

import ecommerce.entities.ProductCategory;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.ProductCategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/productCategory")
public class ProductCategoryController {
    private final ProductCategoryServiceImpl productCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductCategory>> getProductCategory(@PathVariable Long id) throws NotFoundException {
        Optional<ProductCategory> productCategory = productCategoryService.getProductCategory(id);
        return ResponseEntity.ok(productCategory);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategory>> getProductCategories(){
        List<ProductCategory> productCategories = productCategoryService.getProductCategories();
        return ResponseEntity.ok(productCategories);
    }
}
