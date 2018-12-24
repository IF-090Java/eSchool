package academy.softserve.eschool.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name="teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of=("id"))
@ToString(of = {"id", "login"})
public class Teacher extends User{
    
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teacher")
    private Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();

    @Builder
    public Teacher(String login, String password, String email, Role role, String firstName, String lastName,
                   String patronymic, LocalDate dateOfBirth, Sex sex, String phone, String avatar, String description, boolean enabled) {
        super(login, password, email, role, firstName, lastName, patronymic, dateOfBirth, sex, phone, avatar,
                description, enabled);
    }

}
