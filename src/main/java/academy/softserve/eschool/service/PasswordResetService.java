package academy.softserve.eschool.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.auxiliary.PasswordResetTokenGenerator;
import academy.softserve.eschool.dto.PasswordResetDTO;
import academy.softserve.eschool.model.PasswordResetToken;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.PasswordResetTokenRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.CustomPasswordEncoder;
import academy.softserve.eschool.service.base.EmailServiceBase;
import academy.softserve.eschool.service.base.PasswordResetServiceBase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService implements PasswordResetServiceBase{
    private final static String PASSWORD_RESET_EMAIL = "<div><p>Для відновлення паролю перейдіть за посиланням:</p>"
            + "<a href=\"%1$s\">%1$s</a><p>Дане посилання буде активним протягом години<p></div>";
    //TODO change host to appropriate host address
    private final static String PASSWORD_RESET_LINK = "%s/ui/resetPassword?token=%s";
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${academy.softserve.eschool.host}")
    private String host;
    
    @NonNull
    private CustomPasswordEncoder passwordEncoder;
    
    @NonNull
    private PasswordResetTokenRepository passwordResetTokenRepo;
    
    @NonNull
    private EmailServiceBase mailService;
    
    @NonNull
    private UserRepository userRepo;
    
    @NonNull
    private PasswordResetTokenRepository passwordTokenRepo;
    
    @Override
    public String trySendPasswordResetEmail(String query) {
        String message;
        User user = userRepo.findByLoginOrEmail(query, query);
        if (user == null) {
            return "Не вдалося знайти користувача з такими даними";
        }
        
        String email = user.getEmail();
        if (email != null) {
            String token = PasswordResetTokenGenerator.generateToken();
            passwordResetTokenRepo.save(new PasswordResetToken(token, user.getId()));
            mailService.sendHtmlMessage(
                    email,
                    "Відновлення паролю",
                    String.format(PASSWORD_RESET_EMAIL, String.format(PASSWORD_RESET_LINK, host, token)));
            message = "Посилання для відновлення паролю відправлено на Вашу пошту";
        } else {
            message = "Для відновлення паролю зв'яжіться з адміністратором";
        }
        return message;
    }

    @Override
    public String tryChangePassword(PasswordResetDTO passwordDTO) {
        String message;
        Optional<PasswordResetToken> token = passwordTokenRepo.findOptionalByToken(passwordDTO.getToken());
        logger.debug("Recovery token [{}], is present [{}]", passwordDTO.getToken(), token.isPresent());
        if (token.isPresent()) {
            long userId = token.get().getUserId();
            User user = userRepo.getOne((int)userId);
            logger.info("Setting password for userID [{}], user [{}]", userId, user);
            user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
            userRepo.save(user);
            message = "Пароль оновлено!";
            passwordTokenRepo.delete(token.get());
            logger.debug("PasswordResetToken removed from database");
        } else {
            message = "Посилання застаріло";
        }
        return message;
    }

}
