package ac.uk.bolton.ecommercebackend.repository;

import ac.uk.bolton.ecommercebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
    List<Product> findAllByCategoryId(Long categoryId);
}
