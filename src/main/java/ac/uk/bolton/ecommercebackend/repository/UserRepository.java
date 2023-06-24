package ac.uk.bolton.ecommercebackend.repository;

import ac.uk.bolton.ecommercebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
