package ecommerce.dtos.mappers;

import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.services.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserServiceImpl.class})
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    public User postUserRequestToUser(PostUserRequest request);

    public UserResponse userToUserResponse(User user);
}
