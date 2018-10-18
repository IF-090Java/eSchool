package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@Data
@NoArgsConstructor
public class TeacherJournalDTO {

    @ApiModelProperty(notes = "teacher to add")
    private TeacherNamesDTO teacher;     //teacher we want to add to the journal

    @ApiModelProperty(notes = "class of the journal")
    private ClassDTO classs;    //class of the journal that we want to add to the teacher

    @ApiModelProperty(notes = "teacher's subject")
    private SubjectDTO subject;     //teacher's subject

    public TeacherJournalDTO(TeacherNamesDTO teacher, ClassDTO classs, SubjectDTO subject) {
        this.teacher = teacher;
        this.classs = classs;
        this.subject = subject;
    }

    public TeacherNamesDTO getTeacher() {
        return teacher;
    }

    public ClassDTO getClasss() {
        return classs;
    }

    public SubjectDTO getSubject() {
        return subject;
    }
}
