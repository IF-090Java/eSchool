package academy.softserve.eschool.controller;

import java.util.List;

import academy.softserve.eschool.service.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * The controller {@code SubjectController} contains methods, that
 * mapped to the special URL patterns (API Endpoints) for working with subjects
 * and receive requests from {@link org.springframework.web.servlet.DispatcherServlet}.
 * Methods return raw data back to the client in JSON representations.
 *
 * @author Ihor Kudiarskyi
 */
@RestController
@RequestMapping("/subjects")
@Api(value = "Subject endpoints", description = "Operations with subjects")
@RequiredArgsConstructor
public class SubjectController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);

	@NonNull
	private SubjectService subjectService;

	/**
     * Returns a list of {@link SubjectDTO} objects with all available subjects.
     * If {@code classId} request parameter set,
     * returns list of {@link SubjectDTO} objects with subjects that study in classes with the specified id.
     *
     * @param classId Id of class
     * @return List of {@link SubjectDTO} objects with all subjects,
     *         or with subject that study in classes with specified id
     *         in {@link GeneralResponseWrapper} with HttpStatus code
     */
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Admin or teacher gets all subjects", extensions = {@Extension(name = "roles", properties = {
			@ExtensionProperty(name = "teacher", value = "a teacher is allowed to view information about subjects"),
			@ExtensionProperty(name = "admin", value = "the admin is allowed to view information about subjects")})})
	@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
	@GetMapping()
	public GeneralResponseWrapper<List<SubjectDTO>> getAllSubjects(
			@ApiParam(value = "Only subjects studied in specified class will be returned") @RequestParam(required = false) Integer classId) {
		if (classId == null) {
			return new GeneralResponseWrapper<>(Status.of(OK), subjectService.getAllSubjects());
		} else {
			return new GeneralResponseWrapper<>(Status.of(OK), subjectService.getSubjectsByClass(classId));
		}
	}

	/**
     * Returns subject as {@link SubjectDTO} object by subject Id
     *
     * @param id Id of subject
     * @return subject as {@link SubjectDTO} object in {@link GeneralResponseWrapper} with HttpStatus code
     */
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Admin or teacher get a subject by ID", extensions = {@Extension(name = "roles", properties = {
			@ExtensionProperty(name = "teacher", value = "a teacher is allowed to get a subject(that he teaches) by ID"),
			@ExtensionProperty(name = "admin", value = "the admin is allowed to get a subject by ID")})})
	@PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @securityExpressionService.teachesSubject(principal.id, #id))")
	@GetMapping("/{id}")
	public GeneralResponseWrapper<SubjectDTO> getSubjectById(
			@ApiParam(value = "ID of subject", required = true) @PathVariable int id) {
			return new GeneralResponseWrapper<>(Status.of(OK), subjectService.getSubjectById(id));
	}

	/**
     * Returns a list of {@link SubjectDTO} objects with all available subjects.
     * If {@code teacherId} request parameter set, returns list of {@link SubjectDTO} objects
     * with subjects that taught by a teacher with the specified id.
     *
     * @param id Id of teacher
     * @return List of {@link SubjectDTO} objects with subject that taugh by a teacher with the specified id
     *         in {@link GeneralResponseWrapper} with HttpStatus code
     */
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Teacher gets all subjects by teacher", response = SubjectDTO.class, extensions = {@Extension(name = "roles", properties = {
			@ExtensionProperty(name = "teacher", value = "a teacher is allowed to get all the subjects he teaches")})})
	@PreAuthorize("hasRole('TEACHER') and principal.id == #id")
	@GetMapping("/teachers/{id}")
	public GeneralResponseWrapper<List<SubjectDTO>> getSubjectsTeacher(
			@ApiParam(value = "ID of teacher", required = true) @PathVariable int id) {
			return new GeneralResponseWrapper<>(Status.of(OK), subjectService.getSubjectsByTeacher(id));
	}

	/**
     * Add new subject
     *
     * @param newSubject New subject object
     * @return Created subject as {@link SubjectDTO} object
     * 		   in {@link GeneralResponseWrapper} with HttpStatus code
     */
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Subject successfully created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Admin adds new subject", extensions = {@Extension(name = "roles", properties = {
			@ExtensionProperty(name = "admin", value = "the admin is allowed to create a new subject")})})
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public GeneralResponseWrapper<SubjectDTO> addSubject(
			@ApiParam(value = "Subject object", required = true) @RequestBody SubjectDTO newSubject) {
		
			LOGGER.info("Adding a new subject [{}]", newSubject.getSubjectName());
			return new GeneralResponseWrapper<>(Status.of(CREATED), subjectService.addSubject(newSubject));
	}
	
	/**
     * Edit some subject
     *
     * @param id Id of subject
     * @param editSubject {@link SubjectDTO} object of subject that need to be edited
     * @return Edited subject as {@link SubjectDTO} object
     *         in {@link GeneralResponseWrapper} with HttpStatus code
     */
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Subject successfully updated"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Admin edits a subject", extensions = {@Extension(name = "roles", properties = {
			@ExtensionProperty(name = "admin", value = "the admin is allowed to edit a subject")})})
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public GeneralResponseWrapper<SubjectDTO> editSubject(
			@ApiParam(value = "ID of object", required = true) @PathVariable int id,
			@ApiParam(value = "Subject object", required = true) @RequestBody SubjectDTO editSubject) {
		
			LOGGER.info("Updating an existing subject with id = [{}] ", id);
			return new GeneralResponseWrapper<>(Status.of(CREATED), subjectService.editSubject(id, editSubject));
	}
}
