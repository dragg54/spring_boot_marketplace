package ecommerce.repositories;

import ecommerce.entities.Bid;
import ecommerce.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByBid(Bid bid);
}
