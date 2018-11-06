package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NYTransitionDTO {
    @ApiModelProperty(required = true, notes = "ID of class from previous year")
    private int oldClassId;

    @ApiModelProperty(required = true, notes = "ID of currently year class")
    private int newClassId;
}
