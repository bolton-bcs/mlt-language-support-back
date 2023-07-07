package ac.uk.bolton.ecommercebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "forgot_password")
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Date created_at;

}
