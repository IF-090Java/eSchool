package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Subject {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(max=50)
	private String name;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "subject")
	private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	
	public Subject(String name) {
		super();
		this.name = name;
	}
}
