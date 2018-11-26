package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    @ApiModelProperty(notes = "Contains the ID of the pupil.")
    private int Id;

    @ApiModelProperty(notes = "Contains the first name of the pupil: " +
            "the name must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The name must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's name can be \" Світлана\" or \" Мар'ян\", but not \" Andriy\" or \" надія\".")
    private String firstname;

    @ApiModelProperty(notes = "Contains the surname of the pupil. " +
            "It has the same rules of input as the first name: " +
            "the surname must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The surname must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's surname can be \" Прусак\", but not \" ПрУсак\" or \" prusak\".  " +
            "It can't be blank.")
    private String lastname;

    @ApiModelProperty(notes = "Contains the patronymic of the pupil. " +
            "It has the same rules of input as the first name: " +
            "the patronymic must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The patronymic must have maximum length 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's patronymic can be \" Андріївна\", but not \" аНдріЇвна\" or \" Andriyivna\". " +
            "It can't be blank.")
    private String patronymic;

    @ApiModelProperty(notes = "Contains the name of the class of the pupil. " +
                    "This field must match the pattern \"\\\\d{1,2}-?[А-ЯІЇЄҐа-яіїєґ]?\", " +
                    "so you have two ways to enter the class's name: \n" +
                    "1) a string that is only composed of one or two numbers. For example: 9 or 10, but not 100.\n" +
                    "2) a string that is composed of one or two numbers, a \"-\" symbol and a single Ukrainian character. " +
                    "For example: \"9-Б\" or \"10-а\", but not \"5-d\".\n" +
                    "The name must have maximum length of 4 symbols and it can't be blank.")
    private String classe;

    @ApiModelProperty(notes = "Contains the ID of the class of the pupil. It's a generated value in the database.")
    private int classId;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            timezone = "Europe/Kiev",
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Contains the date of birth of the pupil in format: \"yyyy-mm-dd\". " +
            "The date must be in the past. For example: \"2002-02-02\", but not \"2019-10-10\" or \"10-10-2003\".")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Contains the pupil's login. This field's maximum length is 100 symbols " +
            "and minimum length - 5 symbols. It can't be blank.")
    private String login;

    @ApiModelProperty(notes = "Contains the pupil's email. It must correspond the email pattern. " +
            "For example: \"email12@gmail.com\", but not \"email12.com\".")
    private String email;

    @ApiModelProperty(notes = "Contains the pupil's phone number. It's maximum length is 20 symbols.")
    private String phone;

    @ApiModelProperty(notes = "Contains the pupil's avatar. The file's maximum size is 500KB (it's encoded to Base64).")
    private String avatar;

    public StudentDTO(String firstname, String lastname, String patronymic, String classe, int classId, LocalDate dateOfBirth, String login, String email, String phone, String avatar) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.classe = classe;
        this.classId = classId;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }
}
