package ecommerce.services;

import ecommerce.dtos.mappers.ProductMapper;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.entities.Product;
import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    @Override
    public void createProduct(PostProductRequest request) {
        LOGGER.info("new product added");
        Product newProduct = productMapper.postProductRequestToProduct(request);
        productRepository.save(newProduct);
    }
    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public void updateProduct(PutProductRequest request, Long id) throws NotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Product with id $d not found", id)));
        existingProduct.setProductCategory(request.getProductCategory());
        existingProduct.setProductName(request.getProductName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setQuantity(request.getQuantity());
        LOGGER.info("new product updated");
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) throws NotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Product with id $d not found", id)));
        productRepository.deleteById(id);
    }
}
