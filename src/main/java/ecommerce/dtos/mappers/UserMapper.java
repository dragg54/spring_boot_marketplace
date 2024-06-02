package ecommerce.dtos.mappers;

import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.services.ProductService;
import ecommerce.services.UserService;
import ecommerce.services.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserServiceImpl.class})
public interface UserMapper {
    public User postUserRequestToUser(PostUserRequest request);

    public UserResponse userToUserResponse(User user);
}
