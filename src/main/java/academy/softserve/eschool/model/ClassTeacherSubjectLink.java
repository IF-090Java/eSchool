package academy.softserve.eschool.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="class_teacher_subject_link")
@Data
@NoArgsConstructor
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
}
