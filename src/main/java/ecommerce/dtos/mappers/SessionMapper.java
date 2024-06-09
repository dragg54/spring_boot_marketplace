package ecommerce.dtos.mappers;

import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.entities.Session;
import ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    @Mapping(target = "status", constant = "OPENED")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Session openSessionRequestToSession(OpenSessionRequest request, User user);

    @Mapping(target = "status", constant = "CHECKED_OUT")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Session checkoutSessionRequestToSession(CheckoutSessionRequest request, User user);
}
