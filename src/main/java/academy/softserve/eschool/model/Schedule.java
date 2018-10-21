package academy.softserve.eschool.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue
	private int id;
	private byte lessonNumber;
	private Date date;
	private String hometask;
	private MarkType markType;
	@Lob
	private byte[] file;
	
	public Schedule(byte lessonNumber, Date date, String hometask, MarkType markType, byte[] file) {
		super();
		this.lessonNumber = lessonNumber;
		this.date = date;
		this.hometask = hometask;
		this.markType = markType;
		this.file = file;
	}
}
