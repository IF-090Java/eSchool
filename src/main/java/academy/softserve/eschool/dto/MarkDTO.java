package academy.softserve.eschool.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO implements Comparable<MarkDTO>{
	private int studentId;
	private int subjectId;
	private int classId;
	private int mark;
	@JsonFormat(
		      shape = JsonFormat.Shape.STRING,
		      pattern = "yyyy-MM-dd")
	private Date date;
	@Override
	public int compareTo(MarkDTO anotherMark) {
		return date.compareTo(anotherMark.getDate());
	}
}
