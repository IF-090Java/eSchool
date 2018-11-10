package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.service.ClassTeacherSubjectServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Connects a teacher with a journal")
@RequiredArgsConstructor
public class TeacherJournalController {

	@NonNull
    private ClassTeacherSubjectServiceImpl classTeacherSubject;
	@NonNull
    private ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;

	//TODO bk do not leave commented code. Remove it or put todo item with the reason why did you left in commented ans when it will be uncommented
 /*   @ApiOperation(value = "Gets a teacher-class-subject connection")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/teachers/{teacherId}/classes/{classId}/subjects/{subjectId}/journal")
    public GeneralResponseWrapper<TeacherJournalDTO> getConections(
            @ApiParam(value = "id of teacher", required = true) @PathVariable("teacherId") final int teacherId,
            @ApiParam(value = "id of class", required = true) @PathVariable("classId") final int classId,
            @ApiParam(value = "id of subject", required = true) @PathVariable("subjectId") final int subjectId)
    {
        ClassTeacherSubjectLink classTeacherSubjectLink =
                classTeacherSubjectLinkRepository.findByIds(teacherId, classId, subjectId);

        return new GeneralResponseWrapper<>(
        		new Status(200, "OK"),
        		new TeacherJournalDTO(classTeacherSubjectLink.getTeacher_id(), classTeacherSubjectLink.getClazz_id(), classTeacherSubjectLink.getSubject_id()));
    }
*/
    @ApiOperation(value = "Connects a teacher with a journal")
    @PostMapping("/teachers/{teacherId}/classes/{classId}/subjects/{subjectId}/journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    public GeneralResponseWrapper<TeacherJournalDTO> postConection(
            @ApiParam(value = "id of teacher", required = true) @PathVariable("teacherId") final int teacherId,
            @ApiParam(value = "id of class", required = true) @PathVariable("classId") final int classId,
            @ApiParam(value = "id of subject", required = true) @PathVariable("subjectId") final int subjectId)
    {
    	classTeacherSubject.saveClassTeacherSubject(new TeacherJournalDTO(teacherId, classId, subjectId), true);
        return new GeneralResponseWrapper<>(new Status(201, "OK"), null);
    }


}
