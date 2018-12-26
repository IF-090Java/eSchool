package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

import static academy.softserve.eschool.model.Clazz.CLASS_NAME_PATTERN;;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDTO {
    @ApiModelProperty(required = false,
            notes = "Contains the ID of the class. It's a generated value in the database.")
    private int id;

    @ApiModelProperty(required = true,
            notes = "Contains the academic year of the class. The minimal value of this field is 2000 (2000 year)." +
                    "It can't be null. For example, it can be: \"2018\", but not \"2.018\"")
    private int classYear;

    @ApiModelProperty(required = true,
            notes = "Contains the name of the class. " +
                    "This field must match the pattern " + CLASS_NAME_PATTERN + " "+
                    "For example: \"9-Б\" or \"10-а\", but not \"5-d\".\n" +
                    "The name must have maximum length of 20 symbols and it can't be blank.")
    private String className;

    @ApiModelProperty(required = false,
            notes = "Contains some additional information about the class. " +
                    "This field can be empty and it's maximum length is 500 symbols.")
    private String classDescription;

    @ApiModelProperty(required = true,
            notes = "Contains the status of the class: if the class is active the value should be TRUE, if not - FALSE. " +
                    "This filed can't be null.")
    private boolean isActive;

    @ApiModelProperty(required = false,
            notes = "Contains the number of students that are actually studying in the specified class. " +
                    "This field is shown only for GET methods.")
    private int numOfStudents;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}