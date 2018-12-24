package academy.softserve.eschool.model;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(callSuper = true, of = "id")
public class Student extends User{

    @ManyToMany(cascade = 
        {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "students_classes", 
            joinColumns = { @JoinColumn(name = "student_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
        )
    @JsonIgnore
    private List<@NotNull Clazz> classes = new ArrayList<>();

    @OneToMany(cascade = 
        {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="student")
    @JsonIgnore
    private List<@NotNull Mark> marks = new ArrayList<>();

    @Builder
    public Student(String login, String password, String email, Role role, String firstName, String lastName,
                   String patronymic, LocalDate dateOfBirth, Sex sex, String phone, String avatar, String description, boolean enabled) {
        super(login, password, email, role, firstName, lastName, patronymic, dateOfBirth, sex, phone, avatar,
                description, enabled);
    }
}


