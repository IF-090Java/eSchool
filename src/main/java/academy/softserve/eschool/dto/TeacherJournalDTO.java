package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * This class represents a connection between a teacher, an active class and a subject.
 *
 * @author Mariana Vorotniak
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherJournalDTO {

    /**
     * teacher we want to add to the journal
     */
    @ApiModelProperty(notes = "Contains the teacher's ID to add to the teacher-class-subject connection. " +
            "It's minimal value is 1. Can't be null.")
    @Min(1)
    private int teacherId;

    /**
     * class of the journal that we want to add to the teacher
     */
    @ApiModelProperty(notes = "Contains the classes ID to add to the teacher-class-subject connection. " +
            "It's minimal value is 1. Can't be null.")
    @Min(1)
    private int classId;

    /**
     * teacher's subject
     */
    @ApiModelProperty(notes = "Contains the subject's ID to add to the teacher-class-subject connection. " +
            "It's minimal value is 1. Can't be null.")
    @Min(1)
    private int subjectId;


}
