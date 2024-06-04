package ecommerce.services;

import ecommerce.data.enums.BidStatus;
import ecommerce.dtos.mappers.ProductMapper;
import ecommerce.dtos.queries.ProductSearchQuery;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.*;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import ecommerce.repositories.ProductCategoryRepository;
import ecommerce.repositories.ProductRepository;
import ecommerce.repositories.UserRepository;
import ecommerce.specifications.ProductSpecification;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;
    private final UserDtlServiceImpl userDtlService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    @Override
    public void createProduct(PostProductRequest request) throws NotFoundException, IOException {
        ProductCategory productCategory = productCategoryRepository.findById(request.getProductCategoryId())
                .orElseThrow(()->new NotFoundException(String.format("Product category with id %d not found",
                        request.getProductCategoryId())));
        Product newProduct = productMapper.postProductRequestToProduct(request, productCategory,
                userDtlService.getCurrentUser());
        List<ProductImage> productImages = new ArrayList<ProductImage>();
        for (MultipartFile file : request.getProductImages()) {
            ProductImage image = new ProductImage();
            image.setProductImage(file.getBytes());
            productImages.add(image);
        }
        Bid newBid = Bid
                .builder()
                .product(newProduct)
                .bidStatus(BidStatus.DRAFTED)
                .createdAt(newProduct.getCreatedAt())
                .build();
         bidRepository.save(newBid);
         newProduct.setProductImages(productImages);
        LOGGER.info("new product added");
        productRepository.save(newProduct);
    }
    @Override
    public ProductResponse getProduct(Long id) throws NotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Product with id %d not found",id
                )));
        ProductResponse response = productMapper.productToProductResponse(product);
        return response;
    }

    @Override
    public List<ProductResponse> getProducts(ProductSearchQuery searchQuery) {
        Specification<Product> productSpecification = ProductSpecification.filterBySearchQuery(searchQuery);
        List<Product> products= productRepository.findAll(productSpecification);
        List<ProductResponse> productResources= productMapper.productsToProductResources(products);
        return productResources;
    }

    @Override
    public void updateProduct(PutProductRequest request, Long id) throws NotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Product with id $d not found", id)));
        existingProduct.setProductCategory(request.getProductCategoryId());
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
