package ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Embeddable
@Data
@Getter
@Setter
public class ProductImage {
    @Lob
    @Column(name = "product_image", columnDefinition = "LONGBLOB")
    private byte[] productImage;
}
