package academy.softserve.eschool.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherDTO {
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String email;
    private String phone;
}
