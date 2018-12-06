package academy.softserve.eschool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.softserve.eschool.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer>{
    /**
     * Searches for password recovery token in database
     * @param token
     * @return Optional<PasswordResetToken>
     */
    Optional<PasswordResetToken> findOptionalByToken(String token);
}
