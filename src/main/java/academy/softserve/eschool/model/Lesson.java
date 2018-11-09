package academy.softserve.eschool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import academy.softserve.eschool.model.Mark.MarkType;
import lombok.*;

@Entity
@Table(name="lesson")
@Data
@EqualsAndHashCode(exclude = {"marks","subject","clazz"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Positive
	@Column(name="lesson_number")
	private byte lessonNumber;
	@NotNull
	@Temporal(TemporalType.DATE)
	@FutureOrPresent
	private Date date;
	@Size(max=500)
	private String hometask;
	@Enumerated(EnumType.STRING)
	@Column(name="mark_type")
	private MarkType markType;
	@Size(max=(int)(1000000*1.4))
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "lesson")
	private File file;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@NotNull
	private Clazz clazz;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@NotNull
	private Subject subject;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "lesson")
	private final Set<@NotNull Mark> marks = new HashSet<>();
	
	public Lesson(byte lessonNumber, Date date, String hometask, MarkType markType, File file, Clazz clazz,
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
