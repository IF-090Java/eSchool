package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Journal's endpoints", description = "Operations with getting journals")
@RequestMapping("/journals")
@RequiredArgsConstructor
public class JournalController {

    @NonNull
    private JournalServiceImpl journalServiceImpl;

    /**
     * @return List of {@link JournalDTO} which contains data of all journals wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "Admin gets the list of all journals", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "the admin is allowed to view the list of all journals")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public GeneralResponseWrapper<List<JournalDTO>> getJournals(){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getJournals());
    }

    /**
     * Returns list of {@link JournalDTO} which contains data of all journals of specific teacher wrapped in {@link GeneralResponseWrapper}
     * @param idTeacher if specified marks are filtered by user id
     * @return List of {@link JournalDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "Teacher gets the list of all teacher's journals", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher or admin", value = "a teacher is allowed to view the list of all his journals")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('TEACHER') and principal.id == #idTeacher) or hasRole('ADMIN')")
    @GetMapping("/teachers/{idTeacher}")
    public GeneralResponseWrapper<List<JournalDTO>> getJournalsTeacher(
            @ApiParam(value = "ID of teacher", required = true) @PathVariable int idTeacher){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getJournalsByTeacher(idTeacher));
    }

    /**
     * Returns list of {@link JournalDTO} which contains data of active journals of specific teacher wrapped in {@link GeneralResponseWrapper}
     * @param idTeacher if specified marks are filtered by user id
     * @return List of {@link JournalDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "Get list of active teacher's journals", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher or admin", value = "a teacher is allowed to view the list of all his active journals")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("(hasRole('TEACHER') and principal.id == #idTeacher) or hasRole('ADMIN')")
    @GetMapping("/teachers/{idTeacher}/active")
    public GeneralResponseWrapper<List<JournalDTO>> getActiveJournalsTeacher(
            @ApiParam(value = "ID of teacher", required = true) @PathVariable int idTeacher){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getActiveJournalsByTeacher(idTeacher));
    }

    /**
     * Returns list of {@link JournalDTO} which contains all journals for specific class wrapped in {@link GeneralResponseWrapper}
     * @param idClass if specified marks are filtered by user id
     * @return List of {@link JournalDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiOperation(value = "Get list of all journals in class", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "admin", value = "only admin is allowed to view the list of all his active journals")})})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/class/{idClass}")
    public GeneralResponseWrapper<List<JournalDTO>> getJournalsForClass(
            @ApiParam(value = "ID of class", required = true) @PathVariable int idClass){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getJournalsByClass(idClass));
    }

    /**
     * Returns list of {@link JournalMarkDTO} which contains student's data and his marks wrapped in {@link GeneralResponseWrapper}
     * @param idSubject is id of subject
     * @param idClass is id of class
     * @return List of {@link JournalMarkDTO} wrapped in {@link GeneralResponseWrapper}
     */
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @ApiOperation(value = "Teacher gets a journal by subjects and classes", extensions = {@Extension(name = "roles", properties = {
            @ExtensionProperty(name = "teacher or admin", value = "a teacher is allowed to view his journal by subjects and classes")})})
    @PreAuthorize("(hasRole('TEACHER') and @securityExpressionService.hasLessonsInClass(principal.id, #idClass, #idSubject)) or hasRole('ADMIN')")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public GeneralResponseWrapper<List<JournalMarkDTO>> getJournalTable(
            @ApiParam(value = "ID of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "ID of class", required = true) @PathVariable int idClass
            ){
        return new GeneralResponseWrapper<>(Status.of(HttpStatus.OK), journalServiceImpl.getJournal(idSubject,idClass));
    }


}
