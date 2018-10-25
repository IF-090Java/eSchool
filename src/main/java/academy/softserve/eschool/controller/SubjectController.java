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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/subjects")
@Api(value = "subjects", description = "API endpoints for subjects")
public class SubjectController {
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;
	
	public static int nextId = 0;

	public static int incrementId() {
		return ++nextId;
	}

	public static List<SubjectDTO> listOfSubjects = new ArrayList<>();

	static {
		listOfSubjects.add(new SubjectDTO(incrementId(), "Історія України",
				"Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Інформатика", "Опис"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Англійська мова", "Гуманітарний навчальний предмет"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Українська мова", "Гуманітарний навчальний предмет"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Українська література", "Гуманітарний навчальний предмет"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Фізика",
				"Природничий навчальний предмет. Починає вивчатись із 7-го класу"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Географія",
				"Природничий навчальний предмет. Починає вивчатись із 6-го класу"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Біологія",
				"Природничий навчальний предмет. Починає вивчатись із 6-го класу"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Математика", "Опис"));
		listOfSubjects.add(new SubjectDTO(incrementId(), "Хімія",
				"Природничий навчальний предмет. Починає вивчатись із 7-го класу"));
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get all subjects", response = SubjectDTO.class)
	@GetMapping()
	public List<SubjectDTO> getAll() {
		return listOfSubjects;
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get all subjects by teacher", response = SubjectDTO.class)
	@GetMapping("/teachers/{idTeacher}")
	public List<SubjectDTO> getSubjectsTeacher(@PathVariable int idTeacher) {
		return subjectServiceImpl.getSubjectsByTeacher(idTeacher);
	}

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Add new subject")
	@PostMapping
	public SubjectDTO addSubject(@RequestBody SubjectDTO newSubject) {
		newSubject.setSubjectId(incrementId());
		listOfSubjects.add(newSubject);
		return newSubject;
	}

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation(value = "Get a subject by Id", response = SubjectDTO.class)
	@GetMapping("/{id}")
	public SubjectDTO getSubjectById(@PathVariable int id) {
		 for(SubjectDTO subjectList : listOfSubjects){
	            if (subjectList.getSubjectId() == id) return subjectList;
	        }
		return new SubjectDTO(incrementId(), "Навчальний предмет", "Опис");
	}

	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiOperation("Edit a subject")
	@PutMapping("/{id}")
	public SubjectDTO editSubjectClass(@PathVariable int id, @RequestBody SubjectDTO editSubject) {
		 for (SubjectDTO subjectList : listOfSubjects){
	            if (subjectList.getSubjectId() == id){
	            	subjectList.setSubjectName(editSubject.getSubjectName());
	            	subjectList.setSubjectDescription(editSubject.getSubjectDescription());
	            	listOfSubjects.set(subjectList.getSubjectId() - 1, subjectList);
	                return subjectList;
	            }
	        }
		return editSubject;
	}
}
