package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import academy.softserve.eschool.service.SubjectService;
import academy.softserve.eschool.service.SubjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/subjects")
@Api(value = "subjects", description = "API endpoints for subjects")
public class SubjectController {
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get all subjects", response = SubjectDTO.class)
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	@GetMapping()
	public List<SubjectDTO> getAll() {
		return subjectServiceImpl.getAll();
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get a subject by Id", response = SubjectDTO.class)
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	@GetMapping("/{id}")
	public SubjectDTO getSubjectById(@PathVariable int id) {
		return subjectServiceImpl.getSubjectById(id);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get all subjects by teacher", response = SubjectDTO.class)
	@PreAuthorize("hasRole('TEACHER')")
	@GetMapping("/teachers/{idTeacher}")
	public List<SubjectDTO> getSubjectsTeacher(@PathVariable int idTeacher) {
		return subjectServiceImpl.getSubjectsByTeacher(idTeacher);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Add new subject")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public SubjectDTO addSubject(@RequestBody SubjectDTO newSubject) {
		 subjectServiceImpl.addSubject(newSubject);
		return newSubject;
	}

	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation("Edit a subject")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public SubjectDTO editSubject(@PathVariable int id, @RequestBody SubjectDTO editSubject) {
		subjectServiceImpl.editSubject(id, editSubject);
		
		return editSubject;
	}
}
