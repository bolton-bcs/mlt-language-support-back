package ac.uk.bolton.ecommercebackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer qty;
    private Boolean status;

}
