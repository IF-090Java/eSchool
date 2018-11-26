package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JournalDTO {
    @ApiModelProperty(notes = "Contains the ID of the subject. The ID is a generated value in the database. It can't be null.")
    private int idSubject;
    
    @ApiModelProperty(notes = "Contains the ID of the class. The ID is a generated value in the database. It can't be null.")
    private int idClass;
    
    @ApiModelProperty(notes = "Contains the name of the subject: " +
            "the name must match the pattern \"[А-ЯІЇЄҐ]([А-ЯІЇЄҐ]*[а-яіїєґ]*[' -]?)+\", " +
            "so you should enter only Ukrainian characters and the first one must be capitalized." +
            "The name must have maximum length of 50 symbols. " +
            "For example, the subjects's name can be \"Фізика\", but not \"фізИка\" or \"Fisica\".")
    private String subjectName;
    
    @ApiModelProperty(notes = "Contains the name of the class. " +
            "This field must match the pattern \"\\\\d{1,2}-?[А-ЯІЇЄҐа-яіїєґ]?\", " +
            "so you have two ways to enter the class's name: \n" +
            "1) a string that is only composed of one or two numbers. For example: 9 or 10, but not 100.\n" +
            "2) a string that is composed of one or two numbers, a \"-\" symbol and a single Ukrainian character. " +
            "For example: \"9-Б\" or \"10-а\", but not \"5-d\".\n" +
            "The name must have maximum length of 4 symbols and it can't be blank.")
    private String className;
    
    @ApiModelProperty(notes = "Contains the academic year of the journal. The minimal value of this field is 2000 (2000 year)." +
            "It can't be null. For example, it can be: \"2018\", but not \"2.018\"")
    private int academicYear;
}
