package ecommerce.services;

import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.entities.Product;
import ecommerce.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    void createProduct(PostProductRequest request) throws NotFoundException;
    Product getProduct(Long id);
    List<Product> getProducts();
    void updateProduct(PutProductRequest request, Long id) throws NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;
}
