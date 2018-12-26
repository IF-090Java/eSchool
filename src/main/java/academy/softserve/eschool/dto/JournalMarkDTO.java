package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class JournalMarkDTO {
    @ApiModelProperty(notes = "Contains the ID of the student.")
    private int idStudent;
    
    @ApiModelProperty(notes = "Contains the full name of the pupil (first name + surname + patronymic). " +
            "This three components of the full name must match the pattern \"([А-ЯІЇЄҐ][а-яіїєґ']+[-]?)+\", " +
            "so you should enter only Ukrainian characters and the first one of every component must be capitalized. " +
            "For example: \"Шевченко Діана Сергіївна\", but not \"ШевченкО діана Sergiyivna\".")
    private String studentFullName;
    
    @ApiModelProperty(notes = "Contains an array of marks of the pupil. Every element of the array should have " +
            "the following fields: \n" +
            "1) ID of the lesson the mark was putted. It can't be null.\n" +
            "2) The mark (the maximum value is 12). It can't be null.\n" +
            "3) The date when the mark was putted in format \"yyyy.MM.dd\".\n" +
            "4) The type of the mark. It can be one of the following: Control, Practic, Module or Labaratorna.\n" +
            "5) The note (additional description of the mark). It's maximum length is 200 characters.")
    private List<MarkDescriptionDTO> marks;

}
