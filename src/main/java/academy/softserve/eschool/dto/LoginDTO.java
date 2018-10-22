package academy.softserve.eschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String login;
    private String password;
}
