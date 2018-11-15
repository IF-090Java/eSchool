package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * This class represents a schedule for a class.
 *
 * @author Mariana Vorotniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDTO {
    /**
     * date of start of the semester in format "yyyy-MM-dd"
     * it can't be initialized with a date in the past
     */
    @Future
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(required = true, notes = "date")
    @NotNull
    private LocalDate startOfSemester;
    /**
     * date of end of the semester in format "yyyy-MM-dd"
     * it can't be initialized with a date in the past
     */
    @Future
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "end of the semester")
    @NotNull
    private LocalDate endOfSemester;
    /**
     * ClassDTO {@link ClassDTO} object for which is the schedule created
     */
    @ApiModelProperty(notes = "name of the class")
    @NotNull
    private ClassDTO className;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Monday schedule
     */
    @ApiModelProperty(notes = "schedule for Monday")
    private List<SubjectDTO> mondaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Tuesday schedule
     */
    @ApiModelProperty(notes = "schedule for Tuesday")
    private List<SubjectDTO> tuesdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Wednesday schedule
     */
    @ApiModelProperty(notes = "schedule for Wednesday")
    private List<SubjectDTO> wednesdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Thursday schedule
     */
    @ApiModelProperty(notes = "schedule for Thursday")
    private List<SubjectDTO> thursdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Friday schedule
     */
    @ApiModelProperty(notes = "schedule for Friday")
    private List<SubjectDTO> fridaySubjects;

}
