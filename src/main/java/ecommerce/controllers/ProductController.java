package ecommerce.controllers;

import ecommerce.dtos.queries.ProductSearchQuery;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.Product;
import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity createProduct(@ModelAttribute("request") PostProductRequest request,
                                        @RequestParam("productImages") List<MultipartFile> productImages)
            throws NotFoundException, IOException {
        request.setProductImages(productImages);
        productService.createProduct(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(@ModelAttribute ProductSearchQuery searchQuery){
        List<ProductResponse> products = productService.getProducts(searchQuery);
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) throws NotFoundException {
        ProductResponse product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) throws NotFoundException {
        productService.deleteProduct(id);
    }
}
