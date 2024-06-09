package ecommerce.controllers;

import com.stripe.exception.StripeException;
import ecommerce.dtos.requests.CheckoutSessionRequest;
import ecommerce.dtos.requests.OpenSessionRequest;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.SessionServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
public class SessionController {
    private final SessionServiceImp sessionServiceImp;

    @PostMapping
    public ResponseEntity openSession(OpenSessionRequest request) throws NotFoundException {
        sessionServiceImp.openSession(request);
        return ResponseEntity.ok("Session opened");
    }

    @PutMapping
    public ResponseEntity checkoutSession(CheckoutSessionRequest request, @PathVariable Long id)
            throws StripeException, NotFoundException {
        sessionServiceImp.checkoutSession(request, id);
        return ResponseEntity.ok("Session updated to checked out");
    }
}
