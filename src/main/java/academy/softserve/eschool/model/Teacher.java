package academy.softserve.eschool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="teacher")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{
	
	@OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teacher")
	private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();

	public Teacher(String login, String password, String email, Role role, String firstName, String lastName,
			String patronymic, Date dateOfBirth, Sex sex, String phone, String avatar, String description) {
		super(login, password, email, role, firstName, lastName, patronymic, dateOfBirth, sex, phone, avatar,
				description);
	}
	
	

}
