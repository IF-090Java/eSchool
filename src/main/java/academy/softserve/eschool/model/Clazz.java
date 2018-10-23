package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Clazz {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(max=4)
	private String name;
	@Size(max=500)
	private String description;
	@NotBlank
	@Min(value=2000)
	private int academicYear;
	@NotNull
	private boolean isActive;
	@ManyToMany(mappedBy = "classes", 
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<@NotNull Student> students = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
	private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
	private Set<@NotNull Lesson> schedule = new HashSet<>();
	
	public Clazz(String name, String description, int academicYear, boolean isActive) {
		super();
		this.name = name;
		this.description = description;
		this.academicYear = academicYear;
		this.isActive = isActive;
	}
}
