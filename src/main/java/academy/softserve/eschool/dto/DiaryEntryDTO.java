package academy.softserve.eschool.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryEntryDTO {
	private Date date;
	private int lessonNumber;
	private String subjectName;
	private String homeWork;
	private int mark;
	private String note;
}
