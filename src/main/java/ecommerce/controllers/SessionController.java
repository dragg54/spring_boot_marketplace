package ecommerce.controllers;

import com.stripe.exception.StripeException;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.SessionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
public class SessionController {
    private final SessionServiceImpl sessionServiceImpl;

    @PostMapping
    public ResponseEntity openSession(@RequestBody  OpenSessionRequest request) throws NotFoundException,
            InvalidRequestException, StripeException, DuplicateException {
        sessionServiceImpl.openSession(request);
        return ResponseEntity.ok("Session opened");
    }

    @PutMapping("{id}")
    public ResponseEntity checkoutSession(@RequestBody  CheckoutSessionRequest request, @PathVariable Long id)
            throws StripeException, NotFoundException, DuplicateException {
        sessionServiceImpl.checkoutSession(request, id);
        return ResponseEntity.ok("Session updated to checked out");
    }
}
