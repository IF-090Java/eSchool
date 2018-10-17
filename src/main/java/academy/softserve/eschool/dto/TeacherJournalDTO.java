package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Mariana on 12.10.2018.
 */
@Data
public class TeacherJournalDTO {

    @ApiModelProperty(notes = "teacher to add")
    private TeacherNamesDTO teacher;     //teacher we want to add to the journal

    @ApiModelProperty(notes = "class of the journal")
    private ClassDTO classs;    //class of the journal that we want to add to the teacher

    @ApiModelProperty(notes = "teacher's subject")
    private String subject;     //teacher's subject

    public TeacherJournalDTO(TeacherNamesDTO teacher, ClassDTO classs, String subject) {
        this.teacher = teacher;
        this.classs = classs;
        this.subject = subject;
    }
}
