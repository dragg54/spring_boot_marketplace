package ecommerce.services;

import ecommerce.dtos.queries.ProductSearchQuery;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.Product;
import ecommerce.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void createProduct(PostProductRequest request) throws NotFoundException, IOException;
    ProductResponse getProduct(Long id) throws NotFoundException;
    List<ProductResponse> getProducts(ProductSearchQuery searchQuery);
    void updateProduct(PutProductRequest request, Long id) throws NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;
}
