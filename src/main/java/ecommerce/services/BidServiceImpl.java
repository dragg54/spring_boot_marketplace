package ecommerce.services;

import ecommerce.dtos.queries.BidSearchQuery;
import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
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
    private final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);

    public void updateBidStatus(PutBidRequest request) throws NotFoundException, InvalidRequestException {
        Bid bid = bidRepository.findById(request.getBidId())
                .orElseThrow(()-> new NotFoundException(String.format("String with id %d " +
                        "cannot be found", request.getBidId())));
        if(request.getBidStatus().getValue() < bid.getBidStatus().getValue()){
            String errMsg = "Status cannot be updated to value less than existing status";
            LOGGER.error(errMsg);
            throw new InvalidRequestException(errMsg);
        }
        bid.setBidPrice(request.getBiddingPrice());
        bid.setBidStatus(request.getBidStatus());
        bid.setUpdatedAt(LocalDateTime.now());

        LOGGER.info("bid updated successfully");
        bidRepository.save(bid);
    }

    public List<Bid> getBids(BidSearchQuery searchQuery){
        List<Bid> bids = bidRepository.findAllBids(searchQuery.getProductOwnerId(), searchQuery.getBidStatus());
        return bids;
    }
}
