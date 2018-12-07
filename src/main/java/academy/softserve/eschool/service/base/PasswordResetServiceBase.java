package academy.softserve.eschool.service.base;

import academy.softserve.eschool.dto.PasswordResetDTO;

public interface PasswordResetServiceBase {
    String trySendPasswordResetEmail(String username);
    String tryChangePassword(PasswordResetDTO passwordDTO);
}
