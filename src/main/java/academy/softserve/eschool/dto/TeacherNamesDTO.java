package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TeacherNamesDTO {

    @ApiModelProperty(notes = "Contains the ID of the teacher.")
    private Integer id;
    @ApiModelProperty(notes = "Contains the first name of the teacher: " +
            "the name must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The name must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the teacher's name can be \" Світлана\" or \" Мар'ян\", but not \" Andriy\" or \" надія\".")
    private String firstname;
    @ApiModelProperty(notes = "Contains the surname of the teacher. " +
            "It has the same rules of input as the first name: " +
            "the surname must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The surname must have maximum length of 25 symbols and minimum - 3 symbols. " +
            "For example, the teacher's surname can be \" Прусак\", but not \" ПрУсак\" or \" prusak\".  " +
            "It can't be blank.")
    private String lastname;
    @ApiModelProperty(notes = "Contains the patronymic of the teacher. " +
            "It has the same rules of input as the first name: " +
            "the patronymic must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The patronymic must have maximum length 25 symbols and minimum - 3 symbols. " +
            "For example, the teacher's patronymic can be \" Андріївна\", but not \" аНдріЇвна\" or \" Andriyivna\". " +
            "It can't be blank.")
    private String patronymic;

    public Integer getId() {
        return id;
    }
}
