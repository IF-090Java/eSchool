package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.TeacherJournalDTO;
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

/**
 * The controller {@code TeacherJournalController} contains a method, that is
 * mapped to special URL patterns (API Endpoints) for working with classes
 * and receives requests from {@link org.springframework.web.servlet.DispatcherServlet}.
 * Method returns raw data back to the client in JSON representations.
 *
 * @author Mariana Vorotniak
 */
@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Connects a teacher with a journal")
@RequiredArgsConstructor
public class TeacherJournalController {

    @NonNull
    private ClassTeacherSubjectServiceImpl classTeacherSubject;
    /**
     * This POST method creates a connection between a teacher, an active class and a subject.
     * @param teacherId     id of the teacher {@link academy.softserve.eschool.dto.TeacherDTO#id}
     * @param classId       id of the class {@link academy.softserve.eschool.dto.ClassDTO#id}
     * @param subjectId     id of the subject {@link academy.softserve.eschool.dto.SubjectDTO#subjectId}
     * @return              Class of {@link TeacherJournalDTO} wrapped in {@link GeneralResponseWrapper}
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
