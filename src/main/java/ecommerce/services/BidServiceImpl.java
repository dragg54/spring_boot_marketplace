package ecommerce.services;

import ecommerce.dtos.queries.BidSearchQuery;
import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Product;
import ecommerce.entities.User;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BidServiceImpl implements  BidService{
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final UserDtlServiceImpl userDtlService;
    private final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);

    public void updateBidStatus(PutBidRequest request, Long id) throws NotFoundException, InvalidRequestException {
        Bid bid = bidRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("String with id %d " +
                        "cannot be found", id)));
        if(request.getBidStatus().getValue() < bid.getBidStatus().getValue()){
            String errMsg = "Status cannot be updated to value less than existing status";
            LOGGER.error(errMsg);
            throw new InvalidRequestException(errMsg);
        }
        LOGGER.error(String.format("Product with id %d not found", request.getProductId()));
        Product existingProduct = productRepository.findById(request.getProductId()).orElseThrow(
                ()-> new NotFoundException(String.format("Product with id %d not found", request.getProductId()))
        );
        if(existingProduct.getQuantity() < request.getQuantity()){
            String errMsg = String.format("Bidding quantity cannot be greater than product quantity");
            LOGGER.error(errMsg);
            throw new InvalidRequestException(errMsg);
        }
        request.setProductBidderId(userDtlService.getCurrentUserId());
        bid.setBidPrice(request.getBiddingPrice());
        bid.setBidStatus(request.getBidStatus());
        bid.setUpdatedAt(LocalDateTime.now());
        LOGGER.info("bid updated successfully");
        bidRepository.save(bid);
    }

    @Override
    public List<Bid> bids(BidSearchQuery searchQuery) {
        return null;
    }

    public List<Bid> getBids(BidSearchQuery searchQuery){
        List<Bid> bids = bidRepository.findAllBids(searchQuery.getProductId(), searchQuery.getBidStatus());
        return bids;
    }
}
