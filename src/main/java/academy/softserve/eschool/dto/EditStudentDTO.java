package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditStudentDTO {

    @ApiModelProperty(notes = "contains student first name")
    private String firstname;

    @ApiModelProperty(notes = "contains student last name")
    private String lastname;

    @ApiModelProperty(notes = "contains a patronymic")
    private String patronymic;

    @ApiModelProperty(notes = "contains student class")
    private Integer classId;

    @ApiModelProperty(notes = "contains date of birth of the student yyyy-mm-dd")
    private String dateOfBirth;

    @ApiModelProperty(notes = "student login")
    private String login;

    @ApiModelProperty(notes = "old password")
    private String oldPass;

    @ApiModelProperty(notes = "New password")
    private String newPass;

    @ApiModelProperty(notes = "student email")
    private String email;

    @ApiModelProperty(notes = "student phone")
    private String phone;
}
