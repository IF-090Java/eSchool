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
    @ApiModelProperty(required = true, notes = "Contains the average mark.")
    private double y;
    
    @ApiModelProperty(required = true, notes = "Contains the date when the mark was putted.")
    private LocalDate x;
    
    @ApiModelProperty(required = true, notes = "Contains the count of marks in current date.")
    private int weight;
}
