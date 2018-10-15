package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StudentDTO {
    @ApiModelProperty(notes = "contains student first name")
    private String firstname;

    @ApiModelProperty(notes = "contains student last name")
    private String lastname;

    @ApiModelProperty(notes = "contains student class")
    private String classe;

    @ApiModelProperty(notes = "student login")
    private String login;

    @ApiModelProperty(notes = "student email")
    private String email;

    @ApiModelProperty(notes = "student phone")
    private String phone;
}
