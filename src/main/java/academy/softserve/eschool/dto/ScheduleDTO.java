package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created by Mariana on 12.10.2018.
 */
@Data
public class ScheduleDTO {

    @ApiModelProperty(notes = "id of the schedule")
    private int id_schedule;

    @ApiModelProperty(notes = "start of the semester")
    private Date startOfSemester;

    @ApiModelProperty(notes = "end of the semester")
    private Date endOfSemester;

    @ApiModelProperty(notes = "name of the class")
    private String theClass;

    @ApiModelProperty(notes = "schedule for Monday")
    private Map<Integer, String> mondaySubjects;    //schedule for Monday

    @ApiModelProperty(notes = "schedule for Tuesday")
    private Map<Integer, String> tuesdaySubjects;   //schedule for Tuesday

    @ApiModelProperty(notes = "schedule for Wednesday")
    private Map<Integer, String> wednesdaySubjects; //schedule for Wednesday

    @ApiModelProperty(notes = "schedule for Thursday")
    private Map<Integer, String> thursdaySubjects;  //schedule for Thursday

    @ApiModelProperty(notes = "schedule for Friday")
    private Map<Integer, String> fridaySubjects;    //schedule for Friday

    public ScheduleDTO(int id_schedule, Date startOfSemester, Date endOfSemester, String theClass, Map<Integer, String> mondaySubjects,
                             Map<Integer, String> tuesdaySubjects, Map<Integer, String> wednesdaySubjects, Map<Integer, String> thursdaySubjects,
                             Map<Integer, String> fridaySubjects) {
        this.id_schedule = id_schedule;
        this.startOfSemester = startOfSemester;
        this.endOfSemester = endOfSemester;
        this.theClass = theClass;
        this.mondaySubjects = mondaySubjects;
        this.tuesdaySubjects = tuesdaySubjects;
        this.wednesdaySubjects = wednesdaySubjects;
        this.thursdaySubjects = thursdaySubjects;
        this.fridaySubjects = fridaySubjects;
    }
}
