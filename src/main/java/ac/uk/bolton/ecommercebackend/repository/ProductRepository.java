package ac.uk.bolton.ecommercebackend.repository;

import ac.uk.bolton.ecommercebackend.dto.ProductDTO;
import ac.uk.bolton.ecommercebackend.entity.Product;
import ac.uk.bolton.ecommercebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
}
