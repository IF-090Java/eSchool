package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("This DTO class is used to create login.")
public class DataForLoginDTO {
    @ApiModelProperty(notes = "Contains the first name of the user (teacher or pupil): " +
            "the name must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The name must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the teacher's name can be \" Світлана\" or \" Мар'ян\", but not \" Andriy\" or \" надія\".")
    private String firstName;

    @ApiModelProperty(notes = "Contains the surname of the user (teacher or pupil). " +
            "It has the same rules of input as the first name: " +
            "the surname must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The surname must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's surname can be \" Прусак\", but not \" ПрУсак\" or \" prusak\". " +
            "It can't be blank.")
    private String lastName;

    @ApiModelProperty(notes = "Contains the patronymic of the user (teacher or pupil). " +
            "It has the same rules of input as the first name: " +
            "the patronymic must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The patronymic must have maximum length 25 symbols and minimum - 3 symbols. " +
            "For example, the pupil's patronymic can be \" Андріївна\", but not \" аНдріЇвна\" or \" Andriyivna\". " +
            "It can't be blank.")
    private String patronymic;

    @ApiModelProperty(notes = "Contains the user's login. This field's maximum length is 100 symbols " +
            "and minimum length - 5 symbols. It can't be blank.")
    private String login;

}
