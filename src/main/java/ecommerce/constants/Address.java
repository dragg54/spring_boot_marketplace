package ecommerce.constants;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Address {
    private String street;
    private String state;
    private String country;
}
