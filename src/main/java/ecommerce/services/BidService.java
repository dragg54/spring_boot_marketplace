package ecommerce.services;

import ecommerce.dtos.queries.BidSearchQuery;
import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    public void updateBidStatus(PutBidRequest request, Long id) throws NotFoundException, InvalidRequestException;
    public List<Bid> bids(BidSearchQuery searchQuery);
}
