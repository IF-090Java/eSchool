package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Used for creating teacher with .html.
 */
@Data
public class AddTeacherDTO {
    @ApiModelProperty(notes = "contains teacher first name")
    private String firstName;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastName;

    @ApiModelProperty(notes = "contains teacher subjects")
    private String subject;

    @ApiModelProperty(notes = "contains classes where this teacher work")
    private String classe;

    public AddTeacherDTO(String firstName, String lastName, String subject, String classe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.classe = classe;
    }
}
