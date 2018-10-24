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
public class MarkDataPointDTO implements Comparable{
	@ApiModelProperty(required = true, notes = "mark")
	private double y;
	@JsonFormat(
		      shape = JsonFormat.Shape.STRING,
		      pattern = "yyyy-MM-dd")
	@ApiModelProperty(required = true, notes = "date")
	private Date x;
	
	@Override
	public int compareTo(Object arg0) {
		Date otherDate = ((MarkDataPointDTO) arg0).getX();
		if(this.x.before(otherDate)) {
			return -1;
		} else if (this.x.after(otherDate)) {
			return 1;
		} else {
			return 0;
		}
	}
}
