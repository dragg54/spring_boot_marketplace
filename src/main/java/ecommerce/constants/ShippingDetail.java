package ecommerce.constants;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@Embeddable
public class ShippingDetail {
    private Address address;
    private String telephone1;
    private String telephone2;
    private String email;
}
