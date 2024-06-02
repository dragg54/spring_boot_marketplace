package ecommerce.dtos.responses;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class AuthorizationResponse {
    public String token;
}