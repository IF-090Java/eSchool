package academy.softserve.eschool.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryEntryDTO {
	@ApiModelProperty(required = true, notes = "lesson date")
	private Date date;
	@ApiModelProperty(required = true, notes = "lesson number")
	private int lessonNumber;
	@ApiModelProperty(required = true, notes = "name of lesson")
	private String subjectName;
	@ApiModelProperty(notes = "home work")
	private String homeWork;
	@ApiModelProperty(notes = "mark received by student")
	private int mark;
	@ApiModelProperty(notes = "teachers note")
	private String note;
}
