package ecommerce.constants;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ShippingDetail {
    private Address address;
    private String telephone1;
    private String telephone2;
    private String email;
}
