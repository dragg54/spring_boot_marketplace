package ecommerce.dtos.requests;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public class PostUserRequest {
        private String username;
        private String password;
        private Date createdAt;
        private Date updatedAt;
    }
