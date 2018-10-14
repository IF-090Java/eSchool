package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Used for creating student with .html.
 */
@Data
public class AddStudentDTO {
    @ApiModelProperty(notes = "contains student first name")
    private String firstName;

    @ApiModelProperty(notes = "contains student last name")
    private String lastName;

    @ApiModelProperty(notes = "contains student class")
    private String classe;

    public AddStudentDTO(String firstName, String lastName, String classe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classe = classe;
    }
}
