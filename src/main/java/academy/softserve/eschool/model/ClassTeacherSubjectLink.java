package academy.softserve.eschool.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	private boolean isActive;
	
	public ClassTeacherSubjectLink(Clazz clazz, Teacher teacher, Subject subject, boolean isActive) {
		super();
		this.clazz = clazz;
		this.teacher = teacher;
		this.subject = subject;
		this.isActive = isActive;
	}
}
