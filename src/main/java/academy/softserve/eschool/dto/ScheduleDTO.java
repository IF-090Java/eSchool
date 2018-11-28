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
            notes = "Contains a class object. " +
                    "The name of the class must match the pattern \"\\\\d{1,2}-?[А-ЯІЇЄҐа-яіїєґ]?\", " +
                    "so you have two ways to enter the class's name: \n" +
                    "1) a string that is only composed of one or two numbers. For example: 9 or 10, but not 100.\n" +
                    "2) a string that is composed of one or two numbers, a \"-\" symbol and a single Ukrainian character. " +
                    "For example: \"9-Б\" or \"10-а\", but not \"5-d\".\n" +
                    "The name must have maximum length of 4 symbols and it can't be blank.")
    @NotNull
    private ClassDTO className;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Monday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Monday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<SubjectDTO> mondaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Tuesday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Tuesday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<SubjectDTO> tuesdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Wednesday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Wednesday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<SubjectDTO> wednesdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Thursday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Thursday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<SubjectDTO> thursdaySubjects;
    /**
     * list of lessons (SubjectDTO objects) {@link SubjectDTO} for Friday schedule
     */
    @ApiModelProperty(notes = "Contains the schedule for Friday. It's an array of subjects. Every element of the array should contain: \n" +
            "1) The ID of the subject\n" +
            "2) The name of the subject (only Ukrainian characters and the first one must be capitalized)\n" +
            "3) The subject description. It's maximum length is 500 characters." )
    private List<SubjectDTO> fridaySubjects;

}
