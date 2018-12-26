package academy.softserve.eschool.dto;

import academy.softserve.eschool.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static academy.softserve.eschool.model.User.NAME_PATTERN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddedUsersDTO {
    @ApiModelProperty(notes = "Contains the first name of the user (teacher or pupil): " +
            "the name must match the pattern " + NAME_PATTERN + ", " +
            "so you should enter only Ukrainian characters." +
            "The name must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the teacher's name can be \" Світлана\" or \" Мар'ян\", but not \" Andriy\" or \" надія\".")
    private String firstname;

    @ApiModelProperty(notes = "Contains the surname of the user (teacher or pupil). " +
            "It has the same rules of input as the first name: " +
            "the surname must match the pattern " + NAME_PATTERN + ", " +
            "so you should enter only Ukrainian characters." +
            "The surname must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's surname can be \" Прусак\", but not \" ПрУсак\" or \" prusak\".  " +
            "It can't be blank.")
    private String lastname;

    @ApiModelProperty(notes = "Contains the patronymic of the user (teacher or pupil). " +
            "It has the same rules of input as the first name: " +
            "the patronymic must match the pattern " + NAME_PATTERN + ", " +
            "so you should enter only Ukrainian characters." +
            "The patronymic must have maximum length 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's patronymic can be \" Андріївна\", but not \" аНдріЇвна\" or \" Andriyivna\". " +
            "It can't be blank.")
    private String patronymic;

    @ApiModelProperty(notes = "Contains the role of the user: " +
            "it must correspond to one of the values of the Role ENUM (TEACHER or USER). " +
            "The role can't be null.")
    private User.Role role;

    @ApiModelProperty(notes = "Contains the user's login. This field's maximum length is 100 symbols " +
            "and minimum length - 5 symbols. It can't be blank.")
    private String login;

    @ApiModelProperty(notes = "Contains the user's password. This field's maximum length is 255 symbols. It can't be blank.")
    private String password;
}
