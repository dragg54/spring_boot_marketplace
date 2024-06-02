package ecommerce.services;

import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.requests.UserLoginRequest;
import ecommerce.dtos.responses.AuthorizationResponse;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.NotFoundException;

import java.util.Optional;

public interface UserService {
    void createUser(PostUserRequest request) throws DuplicateException;
    UserResponse getUser(Long userId) throws NotFoundException;
    AuthorizationResponse registerUser(PostUserRequest request);
    AuthorizationResponse loginUser(UserLoginRequest request);
}
