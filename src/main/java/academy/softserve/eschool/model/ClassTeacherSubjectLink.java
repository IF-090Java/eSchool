package academy.softserve.eschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@IdClass(ClassTeacherSubjectLinkId.class)
@Table(name="class_teacher_subject_link")
@Data
@NoArgsConstructor
@ToString(of = {"clazz_id", "teacher_id", "subject_id"})
public class ClassTeacherSubjectLink{
	@Id
	@Column(name = "clazz_id")
	@NotNull
	private int clazz_id;
	@Id
	@Column(name = "teacher_id")
	@NotNull
	private int teacher_id;
	@Id
	@Column(name = "subject_id")
	@NotNull
	private int subject_id;

	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="clazz_id", insertable = false, updatable = false)
	private Clazz clazz;
	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "teacher_id", insertable = false, updatable = false)
	private Teacher teacher;
	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "subject_id", insertable = false, updatable = false)
	private Subject subject;
	@NotNull
	@Column(name="is_active")
	private boolean isActive;
	
	public ClassTeacherSubjectLink(Clazz clazz, Teacher teacher, Subject subject, boolean isActive) {
		super();
		this.clazz = clazz;
		this.teacher = teacher;
		this.subject = subject;
		this.isActive = isActive;
	}
}
