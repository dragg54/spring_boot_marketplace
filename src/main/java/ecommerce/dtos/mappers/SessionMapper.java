package ecommerce.dtos.mappers;

import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Session;
import ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    @Mapping(target = "status", constant = "OPENED")
    @Mapping(target="updatedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Session openSessionRequestToSession(OpenSessionRequest request, User user, Bid bid);

    @Mapping(target = "status", constant = "CHECKED_OUT")
    @Mapping(target="createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Session checkoutSessionRequestToSession(CheckoutSessionRequest request, User user, Session session);
}
