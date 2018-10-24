package academy.softserve.eschool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="lesson")
@Data
@NoArgsConstructor
public class Lesson {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Positive
	@Column(name="lesson_number")
	private byte lessonNumber;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
	@Size(max=500)
	private String hometask;
	@Column(name="mark_type")
	private MarkType markType;
	@Lob
	@Size(max=1_000_000)
	private byte[] file;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@NotNull
	private Clazz clazz;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@NotNull
	private Subject subject;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "lesson")
	private Set<@NotNull Mark> marks = new HashSet<>();
	
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
