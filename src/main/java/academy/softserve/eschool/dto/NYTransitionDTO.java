package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NYTransitionDTO {
    @ApiModelProperty(required = true, notes = "Contains the ID of the class from previous year. " +
            "It should be a positive number (greater than 0).")
    private int oldClassId;

    @ApiModelProperty(required = true, notes = "Contains the ID of new year class. For graduation class send id 0. " +
            "It should be a positive number (greater than 0).")
    private int newClassId;
}
