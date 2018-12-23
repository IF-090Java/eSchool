package academy.softserve.eschool.dto;

import academy.softserve.eschool.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddedStudentDTO extends AddedUsersDTO {
    private String classe;

    @Builder(builderMethodName = "buildStudent")
    public AddedStudentDTO(String firstname, String lastname, String patronymic, User.Role role, String login, String password, String classe) {
        super(firstname, lastname, patronymic, role, login, password);
        this.classe = classe;
    }

}
