package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Clazz {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private int academicYear;
	private boolean isActive;
	@ManyToMany(mappedBy = "classes", 
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Student> students = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Schedule> schedule = new HashSet<>();
	
	public Clazz(String name, String description, int academicYear, boolean isActive) {
		super();
		this.name = name;
		this.description = description;
		this.academicYear = academicYear;
		this.isActive = isActive;
	}
}
