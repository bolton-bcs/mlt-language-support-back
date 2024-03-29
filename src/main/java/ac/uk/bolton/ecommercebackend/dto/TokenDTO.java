package ac.uk.bolton.ecommercebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private long expires_in;
    private String role;
}
