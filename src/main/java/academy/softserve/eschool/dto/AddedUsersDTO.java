package academy.softserve.eschool.dto;

import academy.softserve.eschool.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddedUsersDTO {
    @ApiModelProperty(notes = "contains student first name")
    private String firstname;

    @ApiModelProperty(notes = "contains student last name")
    private String lastname;

    @ApiModelProperty(notes = "contains a patronymic")
    private String patronymic;

    @ApiModelProperty(notes = "Role (teacher or student)")
    private User.Role role;

    @ApiModelProperty(notes = "student login")
    private String login;

    @ApiModelProperty(notes = "Password of user")
    private String password;


}
