package academy.softserve.eschool.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

/**
 * Represents token used to reset user's password
 * @author serhii
 *
 */
@Entity
@Table(name="password_reset_token")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @NonNull
    private String token;
    
    @NonNull
    @Column(name="user_id")
    private int userId;
    
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;
}
