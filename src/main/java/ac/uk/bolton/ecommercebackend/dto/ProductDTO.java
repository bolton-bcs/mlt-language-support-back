package ac.uk.bolton.ecommercebackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private MultipartFile imageUrl;
    private Double price;
    private Integer qty;
    private Boolean status;

}
