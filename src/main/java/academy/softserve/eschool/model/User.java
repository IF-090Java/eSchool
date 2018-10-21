package academy.softserve.eschool.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String login;
	private String password;
	private String email;
	private Role role;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;
    private Sex sex;
    private String phone;
    private String avatar;
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
