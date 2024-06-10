package ecommerce.services;

import ecommerce.constants.BidStatus;
import ecommerce.dtos.requests.PutBidRequest;
import ecommerce.entities.Bid;
import ecommerce.entities.Product;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.repositories.BidRepository;
import static org.junit.jupiter.api.Assertions.*;

import ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BidServiceTest {
    @InjectMocks
    private BidServiceImpl bidService;
    @Mock
    private BidRepository bidRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

//        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Given an updated bid and a put command with status in lower hierarchy to the existing status" +
            "when put bid status is invoked then should throw a bad request exception"
            )
    public void updateBidThrowBadRequestException() throws NotFoundException, InvalidRequestException {
        //Arrange
        Bid bid = Bid
                .builder()
                .bidId(1L)
                .bidStatus(BidStatus.REJECTED)
                .bidPrice(30000)
                .build();
        when(bidRepository.findById(1L)).thenReturn(Optional.of(bid));
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
        PutBidRequest request = PutBidRequest
                .builder()
                .productId(1L)
                .productBidderId(1L)
                .bidStatus(BidStatus.SUBMITTED)
                .build();
        //Act
        //Assert
        assertThrows(InvalidRequestException.class, ()-> bidService.updateBidStatus(request, 1L));
    }
}
