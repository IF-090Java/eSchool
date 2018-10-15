package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TeacherDTO {
    @ApiModelProperty(notes = "contains teacher first name")
    private String firstname;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastname;

    @ApiModelProperty(notes = "teacher login")
    private String login;

    @ApiModelProperty(notes = "teacher email")
    private String email;

    @ApiModelProperty(notes = "teacher phone")
    private String phone;

}
