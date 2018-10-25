package academy.softserve.eschool.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@ToString(of = {"id", "login"})
public class User {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	@Size(max=100, min=5)
	private String login;
	@NotBlank
	@Size(max=40, min=5)
	private String password;
	@Email
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private Role role;
	@NotBlank
	@Size(max=25, min=3)
	@Column(name="first_name")
    private String firstName;
	@NotBlank
	@Size(max=25, min=3)
	@Column(name="last_name")
    private String lastName;
	@NotBlank
	@Size(max=25, min=3)
    private String patronymic;
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
    private Date dateOfBirth;
	@Enumerated(EnumType.STRING)
	@Column(length = 6)
    private Sex sex;
    @Size(max=20)
    private String phone;
    @Size(max=200)
    private String avatar;
    @Size(max=200)
    private String description;
    
	public User(String login, String password, String email, Role role, String firstName, String lastName,
			String patronymic, Date dateOfBirth, Sex sex, String phone, String avatar, String description) {
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
	}
}
