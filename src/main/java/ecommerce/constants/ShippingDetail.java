package ecommerce.constants;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetail {
    @Embedded
    private Address address;
    private String telephone1;
    private String telephone2;
    private String email;
}
