package ac.uk.bolton.ecommercebackend.repository;

import ac.uk.bolton.ecommercebackend.entity.Category;
import ac.uk.bolton.ecommercebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
