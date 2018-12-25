package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import static academy.softserve.eschool.model.User.NAME_PATTERN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserDTO {

    @ApiModelProperty(notes = "Contains the first name of the user: " +
            "the name must match the pattern " + NAME_PATTERN +", " +
            "so you should enter only Ukrainian characters." +
            "The name must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the user's name can be \" Світлана\" or \" Мар'ян\", but not \" Andriy\" or \" надія\".")
    private String firstname;

    @ApiModelProperty(notes = "Contains the surname of the user. " +
            "It has the same rules of input as the first name: " +
            "the surname must match the pattern " + NAME_PATTERN +", " +
            "so you should enter only Ukrainian characters." +
            "The surname must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the user's surname can be \" Прусак\", but not \" ПрУсак\" or \" prusak\". " +
            "It can't be blank.")
    private String lastname;

    @ApiModelProperty(notes = "Contains the patronymic of the user. " +
            "It has the same rules of input as the first name: " +
            "the patronymic must match the pattern " + NAME_PATTERN +", " +
            "so you should enter only Ukrainian characters." +
            "The patronymic must have maximum length 25 symbols and minimum - 3 symbols. " +
            "For example, the user's patronymic can be \" Андріївна\", but not \" аНдріЇвна\" or \" Andriyivna\". " +
            "It can't be blank.")
    private String patronymic;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd", timezone="EET")
    @ApiModelProperty(notes = "Contains the date of birth of the user in format: \"yyyy-mm-dd\". " +
            "The date must be in the past. For example: \"2002-02-02\", but not \"2019-10-10\" or \"10-10-2003\".")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Contains the pupil's login. This field's maximum length is 100 symbols " +
            "and minimum length - 5 symbols. It can't be blank.")
    private String login;

    @ApiModelProperty(notes = "Contains the user's old password. This field's maximum length is 255 symbols. It can't be blank.")
    private String oldPass;

    @ApiModelProperty(notes = "Contains the user's new password. This field's maximum length is 255 symbols. It can't be blank.")
    private String newPass;

    @ApiModelProperty(notes = "Contains the user's email. It must correspond the email pattern. " +
            "For example: \"email12@gmail.com\", but not \"email12.com\".")    private String email;

    @ApiModelProperty(notes = "Contains the user's phone number. It's maximum length is 20 symbols.")
    private String phone;

    @ApiModelProperty(notes = "Contains the user's avatar. The file's maximum size is 500KB (it's encoded to Base64).")
    private String avatar;
}
