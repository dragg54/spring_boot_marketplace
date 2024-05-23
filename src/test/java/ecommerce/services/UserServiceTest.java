package ecommerce.services;

import ecommerce.dtos.mappers.UserMapper;
import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.entities.User;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
            MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Given a valid PostUserRequest when CreateUser is invoked then should" +
            "create a new user and save"
                )
    public void createUser() throws DuplicateException {
        //Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        PostUserRequest request = new PostUserRequest();

        //Act
        userService.createUser(request);

        //Assert
        verify(userRepository).save(userMapper.postUserRequestToUser(request));
    }

    @Test
    @DisplayName("Given a valid id of an existing user when get user is invoked then" +
            "should return the user")
    public void getUser() throws NotFoundException {
        //Arrange
        User user = User.builder().userId(1L).username("test").password("test").build();
        UserResponse userResponse = new UserResponse();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);

        //Act
        UserResponse result = userService.getUser(1L);

        //Asset
        assertNotNull(result);
    }
}