package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import academy.softserve.eschool.constraint.annotation.ClassName;
import academy.softserve.eschool.constraint.annotation.SubjectName;
import lombok.*;

@Entity
@Table(name="clazz")
@EqualsAndHashCode(exclude={"students", "CTSlinks", "schedule"})
@ToString(of = {"id","name"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clazz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	@Size(max=4)
	@ClassName
	private String name;
	@Size(max=500)
	private String description;
	@NotNull
	@Min(value=2000)
	@Column(name="academic_year")
	private int academicYear;
	@NotNull
	@Column(name="is_active")
	private boolean isActive;
	@ManyToMany(mappedBy = "classes", 
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<@NotNull Student> students = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
	private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
	private Set<@NotNull Lesson> schedule = new HashSet<>();

	//todo bk ++ remove all unused constructors.
	public Clazz(String name, String description, int academicYear, boolean isActive) {
		super();
		this.name = name;
		this.description = description;
		this.academicYear = academicYear;
		this.isActive = isActive;
	}
}
