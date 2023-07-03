package ac.uk.bolton.ecommercebackend.repository;

import ac.uk.bolton.ecommercebackend.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    ForgotPassword findByToken(String token);

    ForgotPassword findByEmail(String email);
}
