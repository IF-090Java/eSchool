package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherJournalDTO {

    @ApiModelProperty(notes = "teacher to add")
    @Min(1)
    private int teacher_id;     //teacher we want to add to the journal

    @ApiModelProperty(notes = "class of the journal")
    @Min(1)
    private int class_id;    //class of the journal that we want to add to the teacher

    @ApiModelProperty(notes = "teacher's subject")
    @Min(1)
    private int subject_id;     //teacher's subject


}
