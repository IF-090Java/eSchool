package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkTypeDTO {

    @ApiModelProperty(required = true, notes = "Contains the id of mark type.")
    int id;

    @ApiModelProperty(required = true, notes = "Contains the type of the mark. " +
            "It can be one of the following: Control, Practic, Module or Labaratorna.")
    private String markType;

    @ApiModelProperty(required = true, notes = "Contains a description of the mark type. Max size 500.")
    private String description;

    @ApiModelProperty(required = true, notes = "Contains activity status.")
    private boolean isActive;
}
