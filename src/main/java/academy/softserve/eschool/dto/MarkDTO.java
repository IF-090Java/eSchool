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
public class MarkDTO implements Comparable<MarkDTO>{
	@ApiModelProperty(required = true, notes = "id of student")
	private int studentId;
	@ApiModelProperty(required = true, notes = "id of subject")
	private int subjectId;
	@ApiModelProperty(required = true, notes = "id of student's class")
	private int classId;
	@ApiModelProperty(required = true, notes = "mark")
	private int mark;
	@JsonFormat(
		      shape = JsonFormat.Shape.STRING,
		      pattern = "yyyy-MM-dd")
	@ApiModelProperty(required = true, notes = "date when student received mark")
	private Date date;
	@Override
	public int compareTo(MarkDTO anotherMark) {
		return date.compareTo(anotherMark.getDate());
	}
}
