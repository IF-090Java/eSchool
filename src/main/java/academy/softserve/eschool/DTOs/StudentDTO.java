package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Used for creating student with .html.
 */
@Data
public class StudentDTO {
    @ApiModelProperty(notes = "contains student first name")
    private String firstName;

    @ApiModelProperty(notes = "contains student last name")
    private String lastName;

    @ApiModelProperty(notes = "contains student class")
    private String classe;

    @ApiModelProperty(notes = "student login")
    private String login;

    @ApiModelProperty(notes = "student email")
    private String email;

    @ApiModelProperty(notes = "student phone")
    private String phone;

    public StudentDTO() {
    }

    public StudentDTO(String firstName, String lastName, String classe, String login, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classe = classe;
        this.login = login;
        this.email = email;
        this.phone = phone;
    }
}
