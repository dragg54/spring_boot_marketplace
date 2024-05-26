package ecommerce.repositories;

import ecommerce.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query("SELECT & FROM bid b WHERE b.productOwnerId=:productOwnerId AND bidStatus=?2")
    public List<Bid> findAllBids(@Param("productOwnerId")Long productOwnerId, @Param("bidStatus") String bidStatus);
}
