package ecommerce.controllers;

import ecommerce.dtos.queries.BidSearchQuery;
import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.services.BidServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
@AllArgsConstructor
public class BidController {
    private final BidServiceImpl bidService;

    @PatchMapping("{id}")
    public ResponseEntity updateBidStatus(@PathVariable Long id, @RequestBody PutBidRequest request)
            throws NotFoundException, InvalidRequestException {
        bidService.updateBidStatus(request, id);
        return ResponseEntity.ok("Bid status updated");
    }

    @GetMapping()
    public ResponseEntity getBids(@PathVariable BidSearchQuery searchQuery){
        List<Bid> bids = bidService.getBids(searchQuery);
        return ResponseEntity.ok(bids);
    }
}
