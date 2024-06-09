package ecommerce.dtos.mappers;
import ecommerce.constants.StripeProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ecommerce.entities.Bid;

@Mapper(componentModel = "spring")
public interface StripeMapper {
    @Mapping(target = "productId", source = "bid.product.productId")
    StripeProduct bidToStripeProduct(Bid bid);


}
