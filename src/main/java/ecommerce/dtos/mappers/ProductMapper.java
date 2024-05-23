package ecommerce.dtos.mappers;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.Product;
import ecommerce.services.ProductService;
import ecommerce.services.ProductServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {ProductServiceImpl.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public Product postProductRequestToProduct(PostProductRequest request);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public Product updateProductRequestToProduct(PutProductRequest request);

    public ProductResponse productToProductResponse(Product product);
}
