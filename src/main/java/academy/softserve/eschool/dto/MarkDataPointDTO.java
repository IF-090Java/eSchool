package academy.softserve.eschool.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Date x;
	@ApiModelProperty(required = true, notes = "count of marks in current date")
	private int weight;
}
