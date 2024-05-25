package ecommerce.controllers;

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
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductResponse> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) throws NotFoundException {
        productService.deleteProduct(id);
    }
}
