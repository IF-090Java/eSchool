package academy.softserve.eschool.dto;

import academy.softserve.eschool.model.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDTO {
    @ApiModelProperty(required = false, notes = "ID of class")
    private int id;

    @ApiModelProperty(required = true, notes = "Academic Year")
    private int classYear;

    @ApiModelProperty(required = true, notes = "Class name")
    private String className;

    @ApiModelProperty(required = false, notes = "Some additional information. Can be empty")
    private String classDescription;

    @ApiModelProperty(required = true, notes = "true or false")
    private boolean isActive;

    @ApiModelProperty(required = false, notes = "Num of students in currently class. Only for get methods")
    private int numOfStudents;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}