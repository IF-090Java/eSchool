package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
    @ApiModelProperty(required = true, notes = "ID of class")
    private int Id;

    @ApiModelProperty(required = true, notes = "Class year, from 1 to 12")
    private int classYear;

    @ApiModelProperty(required = false, notes = "Class name, like Б, А. Can be empty")
    private String className;

    @ApiModelProperty(required = false, notes = "Some additional information. Can be empty")
    private String classDescription;
}
