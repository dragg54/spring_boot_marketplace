package ecommerce.constants;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String state;
    private String country;
}
