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
public class StudentDTO {
    @ApiModelProperty(notes = "Id")
    private int Id;

    @ApiModelProperty(notes = "contains student first name")
    private String firstname;

    @ApiModelProperty(notes = "contains student last name")
    private String lastname;

    @ApiModelProperty(notes = "contains a patronymic")
    private String patronymic;

    @ApiModelProperty(notes = "contains student class")
    private String classe;

    @ApiModelProperty(notes = "contains class id")
    private int classId;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            timezone = "Europe/Kiev",
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "contains date of birth of the student yyyy-mm-dd")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "student login")
    private String login;

    @ApiModelProperty(notes = "student email")
    private String email;

    @ApiModelProperty(notes = "student phone")
    private String phone;

    @ApiModelProperty(notes = "Avatar in Base64")
    private String avatar;
}
