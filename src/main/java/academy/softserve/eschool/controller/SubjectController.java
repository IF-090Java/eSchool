package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.service.SubjectService;
import academy.softserve.eschool.service.SubjectServiceImpl;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/subjects")
@Api(value = "subjects", description = "API endpoints for subjects")
public class SubjectController {
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
			})
	@ApiOperation(value = "Get all subjects")
	@GetMapping()
	public List<SubjectDTO> getAll() {
		return subjectServiceImpl.getAll();
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Get a subject by Id")
	@GetMapping("/{idSubject}")
	public SubjectDTO getSubjectById(
			@ApiParam(value = "id of subject", required = true) @PathVariable int idSubject) {
		return subjectServiceImpl.getSubjectById(idSubject);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Get all subjects by teacher", response = SubjectDTO.class)
	@GetMapping("/teachers/{idTeacher}")
	public List<SubjectDTO> getSubjectsTeacher(
			@ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher) {
		return subjectServiceImpl.getSubjectsByTeacher(idTeacher);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Subject successfully created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation(value = "Add new subject")
	@PostMapping
	public SubjectDTO addSubject(
			@ApiParam(value = "subject object", required = true) @RequestBody SubjectDTO newSubject) {
		 subjectServiceImpl.addSubject(newSubject);
		return newSubject;
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Subject successfully updated"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ApiOperation("Edit a subject")
	@PutMapping("/{idSubject}")
	public SubjectDTO editSubject(
			@ApiParam(value = "id of object", required = true) @PathVariable int idSubject,
			@ApiParam(value = "subject object", required = true) @RequestBody SubjectDTO editSubject) {
			subjectServiceImpl.editSubject(idSubject, editSubject);
		return editSubject;
	}
}
