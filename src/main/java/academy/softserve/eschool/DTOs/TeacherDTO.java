package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Used for creating teacher with .html.
 */
@Data
public class TeacherDTO {
    @ApiModelProperty(notes = "contains teacher first name")
    private String firstName;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastName;

    @ApiModelProperty(notes = "teacher login")
    private String login;

    @ApiModelProperty(notes = "teacher email")
    private String email;

    @ApiModelProperty(notes = "teacher phone")
    private String phone;

    public TeacherDTO() {
    }

    public TeacherDTO(String firstName, String lastName, String login, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.phone = phone;
    }
}
