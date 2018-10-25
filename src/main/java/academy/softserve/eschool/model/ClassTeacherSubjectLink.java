package academy.softserve.eschool.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
	private int clazz_id;
	@Id
	@Column(name = "teacher_id")
	private int teacher_id;
	@Id
	@Column(name = "subject_id")
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

	public static class ClassTeacherSubjectLinkId implements Serializable {

		private Clazz clazz;
		private Teacher teacher;
		private Subject subject;

		public ClassTeacherSubjectLinkId() {
		}

		public ClassTeacherSubjectLinkId(Clazz clazz, Teacher teacher, Subject subject) {
			this.clazz = clazz;
			this.teacher = teacher;
			this.subject = subject;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			ClassTeacherSubjectLinkId that = (ClassTeacherSubjectLinkId) o;
			return Objects.equals(clazz, that.clazz) &&
					Objects.equals(teacher, that.teacher) &&
					Objects.equals(subject, that.subject);
		}

		@Override
		public int hashCode() {
			return Objects.hash(clazz, teacher, subject);
		}
	}
}
