package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserDTO {

    @ApiModelProperty(notes = "contains student first name")
    private String firstname;

    @ApiModelProperty(notes = "contains student last name")
    private String lastname;

    @ApiModelProperty(notes = "contains a patronymic")
    private String patronymic;

    @ApiModelProperty(notes = "contains date of birth of the student yyyy-mm-dd")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd", timezone="EST")
    private LocalDate dateOfBirth;

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

    @ApiModelProperty(notes = "avatar")
    private String avatar;
}
