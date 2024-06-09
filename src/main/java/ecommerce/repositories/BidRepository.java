package ecommerce.repositories;

import ecommerce.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query("SELECT b FROM Bid b WHERE b.product.id=:productId AND bidStatus=:bidStatus ")
     List<Bid> findAllBids(@Param("productId")Long productId, @Param("bidStatus") String bidStatus);
}
