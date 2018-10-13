package academy.softserve.eschool.DTOs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;

/**
 * Used for creating teacher with @RequestBody annotation.
 */
@Data
public class AddTeacherDTO {
    @ApiModelProperty(notes = "contains teacher first name")
    private String firstName;

    @ApiModelProperty(notes = "contains teacher last name")
    private String lastName;

    @ApiModelProperty(notes = "contains teacher subjects with classes where does she work")
    private Map<String, HashSet<String>> subjects;
}
