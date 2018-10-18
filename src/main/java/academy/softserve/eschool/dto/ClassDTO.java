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
    private int id;

    @ApiModelProperty(required = false, notes = "Class name")
    private String className;

    @ApiModelProperty(required = false, notes = "Some additional information. Can be empty")
    private String classDescription;
}
