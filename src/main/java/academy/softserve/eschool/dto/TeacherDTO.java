package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    @ApiModelProperty(notes = "Id")
    private int id;

    @ApiModelProperty(notes = "contains teacher first name")
    private String firstname;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastname;

    @ApiModelProperty(notes = "teacher login")
    private String login;

    private String password;

    @ApiModelProperty(notes = "teacher email")
    private String email;

    @ApiModelProperty(notes = "teacher phone")
    private String phone;
}
