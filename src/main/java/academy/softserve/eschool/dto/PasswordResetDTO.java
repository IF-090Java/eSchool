package academy.softserve.eschool.dto;

import lombok.*;

/**
 * Encapsulates password and security token used to update user's password
 * @author serhii
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PasswordResetDTO {
    /**
     * Password
     */
    String password;
    
    /**
     * Token used to identify user, found in password recovery link sent to user's email
     */
    String token;
}
