package academy.softserve.eschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mark {
	@Id
	@GeneratedValue
	private int id;
	private byte mark;
	private String note;
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Student student;
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Schedule lesson;
	
	public Mark(byte mark, String note, Student student, Schedule lesson) {
		super();
		this.mark = mark;
		this.note = note;
		this.student = student;
		this.lesson = lesson;
	}
}
