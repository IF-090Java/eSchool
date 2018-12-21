package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.service.base.MarkServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/marks")
@Api(value = "Marks' endpoints", description="Operations about marks")
@RequiredArgsConstructor
public class MarksController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @NonNull
    private MarkServiceBase markService;

    /**
     * Returns list of {@link MarkDataPointDTO} wrapped in {@link GeneralResponseWrapper}
     * @param studentId if specified marks are filtered by user id
     * @param subjectId if specified marks are filtered by subject id
     * @param classId if specified marks are filtered by class id
     * @param periodStart if specified only marks received after this date are returned
     * @param periodEnd if specified only marks received before this date are returned
     * @return list of {@link MarkDataPointDTO} wrapped in {@link GeneralResponseWrapper}
     */

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")//need access for teacher on statistics page
    @GetMapping("")
    @ApiOperation(value = "Teacher gets marks by date filtered by specified params", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher, admin", value = "a teacher is allowed to view marks by date filtered by specified params")})})
    GeneralResponseWrapper<List<MarkDataPointDTO>> getMarks (
            @ApiParam(value = "filter results by student id") @RequestParam(value = "student_id", required = false) Integer studentId,
            @ApiParam(value = "filter results by subject id") @RequestParam(value = "subject_id", required = false) Integer subjectId,
            @ApiParam(value = "filter results by class id") @RequestParam(value = "class_id", required = false) Integer classId,
            @ApiParam(value = "get marks received after specified date, accepts date in format 'yyyy-MM-dd'") @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate periodStart,
            @ApiParam(value = "get marks received before specified date, accepts date in format 'yyyy-MM-dd'") @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate periodEnd){
        logger.debug("Called getMarks() with params: studentId : [{}], subjectId : [{}], classId : [{}], period : [{} - {}]", studentId, subjectId, classId, periodStart, periodEnd);
        return new GeneralResponseWrapper<>(
                Status.of(OK),
                markService.getFilteredByParams(subjectId, classId, studentId, periodStart, periodEnd));
    }
    
    /**
     * Returns list of strudent's average marks grouped by subject
     * @param studentId
     * @return list of {@link SubjectAvgMarkDTO}
     */
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping("/avg")
    @ApiOperation(value = "Teacher gets student's average marks")
    GeneralResponseWrapper<List<SubjectAvgMarkDTO>> getAverageMarks (
            @ApiParam(value = "student id", required = true) @RequestParam(value = "student_id", required = true) Integer studentId,
            @ApiParam(value = "get average calculated after specified date, accepts date in format 'yyyy-MM-dd'") @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate periodStart,
            @ApiParam(value = "get average calculated before specified date, accepts date in format 'yyyy-MM-dd'") @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate periodEnd){
        logger.debug("Called getAverageMarks() for studentId:[{}], period:[{} - {}]", studentId, periodStart, periodEnd);
        return new GeneralResponseWrapper<List<SubjectAvgMarkDTO>>(
                Status.of(OK),
                markService.getAverageMarks(studentId, periodStart, periodEnd));
    }

    /**
     * Create new mark for transmitted student and lesson.
     * Homework, lesson id and class id required.
     * @param markDTO object with mark (mark, student id and lesson id required).
     * @return Created mark for transmitted student and subject in HomeworkDTO
     *         as {@link MarkDTO} object in {@link GeneralResponseWrapper} with http status code
     */
    @ApiOperation(value = "Teacher saves mark of students by lesson", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher, admin", value = "a teacher is allowed to save marks of students by the lesson he gave")})})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Mark successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("(hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #markDTO.idLesson)) or hasRole('ADMIN')")
    @PostMapping
    public GeneralResponseWrapper<MarkDTO> postMark(
        @ApiParam(value = "mark,note,lesson's id and student's id", required = true) @RequestBody MarkDTO markDTO){
        MarkDTO resultMarkDTO =  markService.saveMark(markDTO);
        return new GeneralResponseWrapper<>(Status.of(CREATED), resultMarkDTO);
    }

    /**
     * Update mark's type of lesson
     * lesson's id and mark's type are required.
     * @param idLesson is id of lesson
     * @param markType is mark's type
     */
    @ApiOperation(value = "Teacher updates mark's type of lesson", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher, admin", value = "a teacher is allowed to update mark's type of the lesson he gave")})})
    @PreAuthorize("(hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idLesson)) or hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping("/lessons/{idLesson}/marktype")
    public GeneralResponseWrapper editType(
            @ApiParam(value = "ID of lesson", required = true) @PathVariable int idLesson,
            @ApiParam(value = "Type of mark", required = true) @RequestBody MarkTypeDTO markType){
        markService.updateType(idLesson, markType.getMarkType());
        return new GeneralResponseWrapper<>(Status.of(CREATED), null);
    }
}
