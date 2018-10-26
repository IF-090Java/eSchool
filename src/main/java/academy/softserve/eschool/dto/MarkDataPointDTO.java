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
	@ApiModelProperty(required = true, notes = "mark")
	private double y;
	@ApiModelProperty(required = true, notes = "date")
	private Date x;
}
