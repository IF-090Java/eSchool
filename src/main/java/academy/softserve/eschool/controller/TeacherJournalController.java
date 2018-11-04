package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.service.ClassTeacherSubjectServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Connects a teacher with a journal")
public class TeacherJournalController {

    @Autowired
    private ClassTeacherSubjectServiceImpl classTeacherSubject;

    @Autowired
    private ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;

    @ApiOperation(value = "Gets a teacher-class-subject connection")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    public GeneralResponseWrapper<TeacherJournalDTO> getConections(
            @ApiParam(value = "id of teacher", required = true) @PathVariable("teacher_id") final int teacher_id,
            @ApiParam(value = "id of class", required = true) @PathVariable("class_id") final int class_id,
            @ApiParam(value = "id of subject", required = true) @PathVariable("subject_id") final int subject_id)
    {
        ClassTeacherSubjectLink classTeacherSubjectLink =
                classTeacherSubjectLinkRepository.findByIds(teacher_id, class_id, subject_id);

        GeneralResponseWrapper<TeacherJournalDTO> response;
        response = new GeneralResponseWrapper<>(new Status(200, "OK"), new TeacherJournalDTO(classTeacherSubjectLink.getTeacher_id(), classTeacherSubjectLink.getClazz_id(),
                classTeacherSubjectLink.getSubject_id()));
        return response;
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @PostMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public GeneralResponseWrapper<TeacherJournalDTO> postConection(
            @ApiParam(value = "id of teacher", required = true) @PathVariable("teacher_id") final int teacher_id,
            @ApiParam(value = "id of class", required = true) @PathVariable("class_id") final int class_id,
            @ApiParam(value = "id of subject", required = true) @PathVariable("subject_id") final int subject_id)
    {
        classTeacherSubject.saveClassTeacherSubject(new TeacherJournalDTO(teacher_id, class_id, subject_id), true);

        GeneralResponseWrapper<TeacherJournalDTO> response;
        response = new GeneralResponseWrapper<>(new Status(201, "OK"), null);
        return response;

    }


}
