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
            notes = "Contains the date of the lesson.")
    private LocalDate date;
    
    @ApiModelProperty(required = true,
            notes = "Contains the lesson number. It's a non-null positive number (greater than 0).")
    private byte lessonNumber;
    
    @ApiModelProperty(required = true,
            notes = "Contains the name of the subject that corresponds the lesson: " +
                    "the name matches the pattern \"[А-ЯІЇЄҐ]([А-ЯІЇЄҐ]*[а-яіїєґ]*[' -]?)+\".")
    private String subjectName;
    
    @ApiModelProperty(required = true, notes = "Contains the homework, given at the lesson. Not null.")
    private String homeWork;
    
    @ApiModelProperty(notes = "Contains the ID of the file attached to the homework.It can be null.")
    private Integer homeworkFileId;
    
    @ApiModelProperty(required = true, notes = "Contains the mark received by the pupil. It's a positive number (greater than 0) " +
            "and it's maximum value is 12.")
    private byte mark;
    
    @ApiModelProperty(required = true, notes = "Contains the teacher's note putted for the pupil. The field's maximum length is 200 characters.")
    private String note;
}
