package ecommerce.dtos.mappers;
import ecommerce.dtos.requests.PostProductRequest;
import ecommerce.dtos.requests.PutProductRequest;
import ecommerce.dtos.responses.ProductResponse;
import ecommerce.entities.Product;
import ecommerce.entities.ProductCategory;
import ecommerce.entities.User;
import ecommerce.services.ProductServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "productImages", ignore = true)
    public Product postProductRequestToProduct(PostProductRequest request, ProductCategory productCategory, User productOwner);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public Product updateProductRequestToProduct(PutProductRequest request);

    public ProductResponse productToProductResponse(Product product);

    public List<ProductResponse> productsToProductResources(List<Product> products);
}
