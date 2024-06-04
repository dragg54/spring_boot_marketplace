package ecommerce.dtos.mappers;

import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BidMapper {
     @Mapping(source="bidder", target = "bidder")
     Bid putBidRequestToBid(@MappingTarget Bid bid, PutBidRequest request, User bidder);
}
