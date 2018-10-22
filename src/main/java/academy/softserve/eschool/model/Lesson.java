package academy.softserve.eschool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Lesson {
	@Id
	@GeneratedValue
	private int id;
	private byte lessonNumber;
	private Date date;
	private String hometask;
	private MarkType markType;
	@Lob
	private byte[] file;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Clazz clazz;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Subject subject;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "lesson")
	private Set<Mark> marks = new HashSet<>();
	
	public Lesson(byte lessonNumber, Date date, String hometask, MarkType markType, byte[] file, Clazz clazz,
			Subject subject) {
		super();
		this.lessonNumber = lessonNumber;
		this.date = date;
		this.hometask = hometask;
		this.markType = markType;
		this.file = file;
		this.clazz = clazz;
		this.subject = subject;
	}
}
