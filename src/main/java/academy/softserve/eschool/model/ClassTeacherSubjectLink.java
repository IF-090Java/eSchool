package academy.softserve.eschool.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="class_teacher_subject_link")
@Data
@NoArgsConstructor
@IdClass(ClassTeacherSubjectLink.ClassTeacherSubjectLinkId.class)
public class ClassTeacherSubjectLink implements Serializable{
	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Clazz clazz;
	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Teacher teacher;
	@Id
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
