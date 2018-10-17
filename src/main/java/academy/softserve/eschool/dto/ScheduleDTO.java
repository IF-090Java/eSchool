package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * Created by Mariana on 12.10.2018.
 */
@Data
@NoArgsConstructor
public class ScheduleDTO {

    @ApiModelProperty(notes = "id of the schedule")
    private long id_schedule;

    @ApiModelProperty(notes = "start of the semester")
    private Date startOfSemester;

    @ApiModelProperty(notes = "end of the semester")
    private Date endOfSemester;

    @ApiModelProperty(notes = "name of the class")
    private ClassDTO className;

    @ApiModelProperty(notes = "schedule for Monday")
    private Map<Integer, SubjectDTO> mondaySubjects;    //schedule for Monday

    @ApiModelProperty(notes = "schedule for Tuesday")
    private Map<Integer, SubjectDTO> tuesdaySubjects;   //schedule for Tuesday

    @ApiModelProperty(notes = "schedule for Wednesday")
    private Map<Integer, SubjectDTO> wednesdaySubjects; //schedule for Wednesday

    @ApiModelProperty(notes = "schedule for Thursday")
    private Map<Integer, SubjectDTO> thursdaySubjects;  //schedule for Thursday

    @ApiModelProperty(notes = "schedule for Friday")
    private Map<Integer, SubjectDTO> fridaySubjects;    //schedule for Friday

    public ScheduleDTO(long id_schedule, Date startOfSemester, Date endOfSemester, ClassDTO className, Map<Integer, SubjectDTO> mondaySubjects,
                             Map<Integer, SubjectDTO> tuesdaySubjects, Map<Integer, SubjectDTO> wednesdaySubjects, Map<Integer, SubjectDTO> thursdaySubjects,
                             Map<Integer, SubjectDTO> fridaySubjects) {
        this.id_schedule = id_schedule;
        this.startOfSemester = startOfSemester;
        this.endOfSemester = endOfSemester;
        this.className = className;
        this.mondaySubjects = mondaySubjects;
        this.tuesdaySubjects = tuesdaySubjects;
        this.wednesdaySubjects = wednesdaySubjects;
        this.thursdaySubjects = thursdaySubjects;
        this.fridaySubjects = fridaySubjects;
    }

}
