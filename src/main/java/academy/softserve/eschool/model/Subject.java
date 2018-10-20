package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Subject {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	
	public Subject(String name) {
		super();
		this.name = name;
	}
}
