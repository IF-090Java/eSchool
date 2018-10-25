package academy.softserve.eschool.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User{

    @ManyToMany(cascade = 
        {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "students_classes", 
            joinColumns = { @JoinColumn(name = "student_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
        )
    @JsonIgnore
    private Set<@NotNull Clazz> classes = new HashSet<>();

    @OneToMany(cascade = 
        {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="student")
    @JsonIgnore
    private Set<@NotNull Mark> marks = new HashSet<>();

	public Student(String login, String password, String email, Role role, String firstName, String lastName,
			String patronymic, Date dateOfBirth, Sex sex, String phone, String avatar, String description) {
		super(login, password, email, role, firstName, lastName, patronymic, dateOfBirth, sex, phone, avatar,
				description);
	}
}


