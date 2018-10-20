package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

//END POINT  /classes/{id}/schedule

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    @ApiModelProperty(notes = "id of the schedule")
    private int id_schedule;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(required = true, notes = "date")
    private Date startOfSemester;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "end of the semester")
    private Date endOfSemester;

    @ApiModelProperty(notes = "name of the class")
    private ClassDTO className;

    @ApiModelProperty(notes = "schedule for Monday")
    private List<SubjectDTO> mondaySubjects;    //schedule for Monday

    @ApiModelProperty(notes = "schedule for Tuesday")
    private List<SubjectDTO> tuesdaySubjects;   //schedule for Tuesday

    @ApiModelProperty(notes = "schedule for Wednesday")
    private List<SubjectDTO> wednesdaySubjects; //schedule for Wednesday

    @ApiModelProperty(notes = "schedule for Thursday")
    private List<SubjectDTO> thursdaySubjects;  //schedule for Thursday

    @ApiModelProperty(notes = "schedule for Friday")
    private List<SubjectDTO> fridaySubjects;    //schedule for Friday

}
