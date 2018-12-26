package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.SubjectRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

	@Mock
	SubjectRepository subjectRepository;

	@InjectMocks
	SubjectServiceImpl subjectService;

	private int subjectId;
	private String subjectName;
	private String subjectDescription;

	private Subject subjectObject;
	private Subject subjectForSave;
	private SubjectDTO expectedSubjectDTO;
	private List<Subject> subjectList = new ArrayList<>();
	private List<SubjectDTO> subjectDTOsList = new ArrayList<>();

	@Before
	public void init() {
		subjectId = 1;
		subjectName = "Історія України";
		subjectDescription = "";

		subjectObject = Subject.builder()
				.id(subjectId)
				.name(subjectName)
				.description(subjectDescription)
				.build();

		subjectForSave = Subject.builder()
				.name(subjectName)
				.description(subjectDescription)
				.build();

		expectedSubjectDTO = SubjectDTO.builder()
				.subjectId(subjectId)
				.subjectName(subjectName)
				.subjectDescription(subjectDescription)
				.build();
	}

	@Test
	public void testGetAllSubjects() {
		subjectList.add(subjectObject);
		subjectDTOsList.add(expectedSubjectDTO);

		Mockito.when(subjectRepository.findAll()).thenReturn(subjectList);
		List<SubjectDTO> actualSubjectList = subjectService.getAllSubjects();
		assertEquals("Getting all subjects", subjectDTOsList, actualSubjectList);
	}

	@Test
	public void testGetSubjectsByTeacher() {
		subjectList.add(subjectObject);
		subjectDTOsList.add(expectedSubjectDTO);

		Mockito.when(subjectRepository.findSubjectsByTeacher(1)).thenReturn(subjectList);
		List<SubjectDTO> actualSubjectList = subjectService.getSubjectsByTeacher(1);
		assertEquals("Getting subject for a specific teacher", subjectDTOsList, actualSubjectList);
	}

	@Test
	public void testGetSubjectsByClass() {
		subjectList.add(subjectObject);
		subjectDTOsList.add(expectedSubjectDTO);

		Mockito.when(subjectRepository.findSubjectsByClass(1)).thenReturn(subjectList);
		List<SubjectDTO> actualSubjectList = subjectService.getSubjectsByClass(1);
		assertEquals("Getting subject for a specific class", subjectDTOsList, actualSubjectList);
	}

	@Test
	public void testGetSubjectById() {
		Mockito.when(subjectRepository.getOne(anyInt())).thenReturn(subjectObject);
		SubjectDTO actualSubject = subjectService.getSubjectById(1);
		assertEquals(expectedSubjectDTO.getSubjectName(), actualSubject.getSubjectName());
		assertEquals(expectedSubjectDTO.getSubjectDescription(), actualSubject.getSubjectDescription());
	}

	@Test
	public void testAddSubject() {
		Mockito.when(subjectRepository.save(subjectForSave)).thenReturn(subjectObject);
		SubjectDTO actualSubject = subjectService
				.addSubject(new SubjectDTO(subjectId, subjectName, subjectDescription));
		assertEquals("Adding a new subject", expectedSubjectDTO, actualSubject);
	}

	@Test
	public void testEditSubject() {
		Mockito.when(subjectRepository.getOne(anyInt())).thenReturn(subjectForSave);
		Mockito.when(subjectRepository.save(subjectForSave)).thenReturn(subjectObject);
		SubjectDTO actualSubject = subjectService.editSubject(1,
				new SubjectDTO(subjectId, subjectName, subjectDescription));
		assertEquals("Updating an existing subject", expectedSubjectDTO, actualSubject);
	}

	@After
	public void destroy() {
		subjectId = 0;
		subjectName = null;
		subjectDescription = null;

		subjectObject = null;
		subjectForSave = null;
		expectedSubjectDTO = null;
		subjectList.clear();
		subjectDTOsList.clear();
	}
}
