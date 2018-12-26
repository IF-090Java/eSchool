package academy.softserve.eschool.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import academy.softserve.eschool.constraint.annotation.RegexPattern;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@ToString(of = {"id", "login"})
public class User {
    public final static String NAME_PATTERN = "[А-ЯІЇЄҐа-яіїєґ()' -]+";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    @Size(max=100, min=5)
    private String login;
    
    @NotBlank
    @Size(max=255)
    private String password;
    
    @Email
    private String email;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @NotBlank
    @Size(max=25, min=3)
    @RegexPattern(pattern=NAME_PATTERN, message = "Input must match " + NAME_PATTERN)
    @Column(name="first_name")
    private String firstName;
    
    @NotBlank
    @Size(max=25, min=3)
    @RegexPattern(pattern=NAME_PATTERN, message = "Input must match " + NAME_PATTERN)
    @Column(name="last_name")
    private String lastName;
    
    @NotBlank
    @Size(max=25, min=3)
    @RegexPattern(pattern=NAME_PATTERN, message = "Input must match " + NAME_PATTERN)
    private String patronymic;
    
    @Past
    @Column(name="date_of_birth")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd", timezone="EET")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    private Sex sex;
    
    @Size(max=20)
    private String phone;
    
    //rough estimation of 500KB base64 encoded
    @Size(max=(int)(500000*1.4))
    private String avatar;
    
    @Size(max=200)
    private String description;

    private boolean enabled;

    public User(String login, String password, String email, Role role, String firstName, String lastName,
            String patronymic, LocalDate dateOfBirth, Sex sex, String phone, String avatar, String description, boolean enabled) {
        super();
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.phone = phone;
        this.avatar = avatar;
        this.description = description;
        this.enabled = enabled;
    }

    public enum Role {
        ROLE_TEACHER, ROLE_USER, ROLE_ADMIN
    }

    public enum Sex {
        male, female
    }
}



