package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Mariana on 12.10.2018.
 */
@Data
public class TeacherJournalDTO {

    @ApiModelProperty(notes = "teacher to add")
    private String teacher;     //teacher we want to add to the journal

    @ApiModelProperty(notes = "class of the journal")
    private String theClass;    //class of the journal that we want to add to the teacher

    @ApiModelProperty(notes = "teacher's subject")
    private String subject;     //teacher's subject

    public TeacherJournalDTO(String teacher, String theClass, String subject) {
        this.teacher = teacher;
        this.theClass = theClass;
        this.subject = subject;
    }
}
