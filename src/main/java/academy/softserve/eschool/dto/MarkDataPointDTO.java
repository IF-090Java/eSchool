package academy.softserve.eschool.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDataPointDTO{
    @ApiModelProperty(required = true, notes = "average mark")
    private double y;
    @ApiModelProperty(required = true, notes = "date")
    private LocalDate x;
    @ApiModelProperty(required = true, notes = "count of marks in current date")
    private int weight;
}
