package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherJournalDTO {

    @ApiModelProperty(notes = "teacher to add")
    private int teacher_id;     //teacher we want to add to the journal

    @ApiModelProperty(notes = "class of the journal")
    private int class_id;    //class of the journal that we want to add to the teacher

    @ApiModelProperty(notes = "teacher's subject")
    private int subject_id;     //teacher's subject


}
