package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Used for creating teacher with .html.
 */
@Data
public class TeacherDTO {
    @ApiModelProperty(notes = "contains teacher first name")
    private String firstName;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastName;

    @ApiModelProperty(notes = "contains teacher subjects")
    private String subject;

    @ApiModelProperty(notes = "contains classes where this teacher work")
    private List<String> classes;

    public TeacherDTO(String firstName, String lastName, String subject, List<String> classes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.classes = classes;
    }
}
