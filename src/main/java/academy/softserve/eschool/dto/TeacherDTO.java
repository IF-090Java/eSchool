package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    @ApiModelProperty(notes = "Id")
    private int id;

    @ApiModelProperty(notes = "contains teacher first name")
    private String firstname;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastname;

    @ApiModelProperty(notes = "contains a patronymic")
    private String patronymic;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            timezone = "Europe/Kiev",
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "contains date of birth of the teacher")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "teacher login")
    private String login;

    @ApiModelProperty(notes = "teacher email")
    private String email;

    @ApiModelProperty(notes = "teacher phone")
    private String phone;

    @ApiModelProperty(notes = "Avatar in Base64")
    private String avatar;
}
