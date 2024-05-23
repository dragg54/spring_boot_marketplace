package ecommerce.services;

import ecommerce.dtos.mappers.UserMapper;
import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    public void createUser(PostUserRequest request) throws DuplicateException {
        User existingUser = userRepository.findByUsername(request.getUsername());
        if(existingUser != null){
            String errmsg = String.format("user with the email already exists");
            LOGGER.error(errmsg);
            throw new DuplicateException(errmsg);
        }
        User newUser = userMapper.postUserRequestToUser(request);
        LOGGER.info("new user created");
        userRepository.save(newUser);
    }

    public UserResponse getUser(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("user with id %d not found", id)));
        LOGGER.info(String.format("user with id %d found", id));
        UserResponse userResponse = userMapper.userToUserResponse(user);
        return userResponse;
    }
}
