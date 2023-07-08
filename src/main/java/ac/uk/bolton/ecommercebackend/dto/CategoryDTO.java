package ac.uk.bolton.ecommercebackend.dto;

import ac.uk.bolton.ecommercebackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> productList;

}
