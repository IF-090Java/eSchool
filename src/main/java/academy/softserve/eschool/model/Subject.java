package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="subject")
public class Subject {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(max=50)
	private String name;
	@Size(max=255)
	private String description;
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "subject")
	private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
	
	public Subject(@NotBlank @Size(max = 50) String name, @Size(max = 255) String description) {
		super();
		this.name = name;
		this.description = description;
	}
}
