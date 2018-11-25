package academy.softserve.eschool.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DiaryEntryDTO {
    
    @ApiModelProperty(required = true,
            notes = "Contains the ID of the lesson. It's a generated value in the database.")
    private int lessonId;
    
    @ApiModelProperty(required = true,
            notes = "Contains the date of the lesson. This field must be filled with a date in the future. " +
                    "For example, if today's date is \"2018-11-25\", the value can be: \"2018-12-01\", " +
                    "but not \"2020-12-01\". It can't be null.")
    private LocalDate date;
    
    @ApiModelProperty(required = true,
            notes = "Contains the lesson number. It should be a positive number (greater than 0) and it can't be null.")
    private byte lessonNumber;
    
    @ApiModelProperty(required = true,
            notes = "Contains the name of the subject that corresponds the lesson: " +
                    "the name must match the pattern \"[А-ЯІЇЄҐ]([А-ЯІЇЄҐ]*[а-яіїєґ]*[' -]?)+\", " +
                    "so you should enter only Ukrainian characters and the first one must be capitalized." +
                    "The name must have maximum length of 50 symbols. " +
                    "For example, the subjects's name can be \"Фізика\", but not \"фізИка\" or \"Fisica\".")
    private String subjectName;
    
    @ApiModelProperty(notes = "Contains the homework, given at the lesson. It can't be null.")
    private String homeWork;
    
    @ApiModelProperty(notes = "Contains the ID of the file attached to the homework.It can be null.")
    private Integer homeworkFileId;
    
    @ApiModelProperty(notes = "Contains the mark received by the pupil. It should be a positive number (greater than 0) " +
            "and it's maximum value is 12.")
    private byte mark;
    
    @ApiModelProperty(notes = "Contains the teacher's note putted for the pupil. The field's maximum length is 200 characters.")
    private String note;
}
