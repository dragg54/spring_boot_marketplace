package ecommerce.services;

import ecommerce.dtos.mappers.UserMapper;
import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.requests.UserLoginRequest;
import ecommerce.dtos.responses.AuthorizationResponse;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
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

    public AuthorizationResponse registerUser(PostUserRequest user){
        System.out.println("user = " + user);
        var newUser = User
                .builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(newUser);
        String token = authService.generateToken(newUser);
        return AuthorizationResponse.builder().token(token).build();
    }

    @Override
    public AuthorizationResponse loginUser(UserLoginRequest user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User userDtl = userRepository.findByUsername(user.getUsername());
        String token = authService.generateToken(userDtl);
        return AuthorizationResponse.builder().token(token).build();
    }
}
