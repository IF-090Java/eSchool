package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import static academy.softserve.eschool.model.Clazz.CLASS_NAME_PATTERN;

import java.time.LocalDate;
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
    @ApiModelProperty(required = true, notes = "Contains the date of start of semester in format \"yyyy-MM-dd\". " +
            "The date must be in the future and can't be null. For example, if today's date is \"2018-11-25\": " +
            "\"2018-11-26\", but not \"26-11-2018\" or \"2017-11-25\".")
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
    @ApiModelProperty(required = true, notes = "Contains the date of end of semester in format \"yyyy-MM-dd\". " +
            "The date must be in the future and be later that the date of start of semester. " +
            "It can't be null. For example, if today's date is \"2018-11-25\", and the date of start of semester is \"2018-11-26\": " +
            "\"2018-11-27\", but not \"2018-11-26\" or \"2017-10-10\".")
    @NotNull
    private LocalDate endOfSemester;
    /**
     * ClassDTO {@link ClassDTO} object for which is the schedule created
     */
    @ApiModelProperty(required = true,
            notes = "Contains the name of the class. " +
                    "This field must match the pattern " + CLASS_NAME_PATTERN + " "+
                    "For example: \"9-Б\" or \"10-а\", but not \"5-d\".\n" +
                    "The name must have maximum length of 20 symbols and it can't be blank.")
    @NotNull
    private ClassDTO className;
    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Monday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Monday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> mondaySubjects;
    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Tuesday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Tuesday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> tuesdaySubjects;
    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Wednesday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Wednesday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> wednesdaySubjects;
    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Thursday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Thursday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> thursdaySubjects;
    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Friday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Friday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> fridaySubjects;

    /**
     * list of lessons (LessonDTO objects) {@link LessonDTO} for Saturday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Saturday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<LessonDTO> saturdaySubjects;

    public int getClassId()
    {
        return className.getId();
    }

}
