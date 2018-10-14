package academy.softserve.eschool.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {
	private int studentId;
	private int subjectId;
	private int classId;
	private int mark;
	private Date date;
}
