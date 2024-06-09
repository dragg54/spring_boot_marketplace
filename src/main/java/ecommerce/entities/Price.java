package ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "bid_id")
    private Bid bid;
}
