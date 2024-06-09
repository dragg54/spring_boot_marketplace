package ecommerce.entities;

import ecommerce.constants.SessionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private String stripePriceId;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "bid_id")
    private Bid bid;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "user_id")
    private User user;
}
