package ecommerce.controllers;

import ecommerce.dtos.requests.PostUserRequest;
import ecommerce.dtos.responses.UserResponse;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody PostUserRequest request) throws DuplicateException {
        userService.createUser(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) throws NotFoundException {
        UserResponse userResponse = userService.getUser(id);
        return ResponseEntity.ok(userResponse);
    }
}
