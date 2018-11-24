package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.ClassRepository;
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
public class ClassServiceImplTest {

    @Mock
    ClassRepository classRepository;

    @InjectMocks
    ClassServiceImpl classService;

    private int classId;
    private String className;
    private int academicYear;
    private String classDescription;
    private boolean isActive;
    private int numOfStudents;

    private Clazz clazzObject;
    private Clazz classToSave;
    private ClassDTO expectedClassDTO;
    private List<Clazz> clazzList;
    private List<ClassDTO> classDTOList;

    @Before
    public void init(){
        classId = 1;
        className = "10-А";
        academicYear = 2018;
        classDescription = "Some new class";
        isActive = true;
        numOfStudents = 0;

        clazzList = new ArrayList<>();
        classDTOList = new ArrayList<>();

        clazzObject = Clazz.builder()
                .id(classId)
                .name(className)
                .academicYear(academicYear)
                .description(classDescription)
                .isActive(isActive)
                .build();

        classToSave = Clazz.builder()
                .name(className)
                .description(classDescription)
                .academicYear(academicYear)
                .isActive(isActive)
                .build();

        expectedClassDTO = ClassDTO.builder()
                .id(classId)
                .className(className)
                .classDescription(classDescription)
                .classYear(academicYear)
                .isActive(isActive)
                .numOfStudents(numOfStudents).build();
    }

    @Test
    public void testSaveClass(){
        Mockito.when(classRepository.save(classToSave)).thenReturn(clazzObject);
        ClassDTO result = classService.saveClass(new ClassDTO(classId, academicYear, className, classDescription, isActive, numOfStudents));
        assertEquals(expectedClassDTO, result);
    }

    @Test
    public void testUpdateClass(){
        Mockito.when(classRepository.getOne(anyInt())).thenReturn(classToSave);
        Mockito.when(classRepository.save(classToSave)).thenReturn(clazzObject);
        ClassDTO result = classService.updateClass(1, new ClassDTO(classId, academicYear, className, classDescription, isActive, numOfStudents));
        assertEquals(expectedClassDTO, result);
    }

    @Test
    public void testFindClassById(){
        Mockito.when(classRepository.getOne(anyInt())).thenReturn(clazzObject);
        ClassDTO result = classService.findClassById(1);
        assertEquals(expectedClassDTO.getClassName(), result.getClassName());
        assertEquals(expectedClassDTO.getClassDescription(), result.getClassDescription());
        assertEquals(expectedClassDTO.getClassYear(), result.getClassYear());
        assertEquals(expectedClassDTO.getIsActive(), result.getIsActive());
    }

    @Test
    public void testGetAllClasses(){
        clazzList.add(clazzObject);
        classDTOList.add(expectedClassDTO);

        Mockito.when(classRepository.findAll()).thenReturn(clazzList);
        List<ClassDTO> classList = classService.getAllClasses();
        assertEquals(classDTOList, classList);
    }

    @Test
    public void testAddNewYearClasses(){
        List<Clazz> getAllClassesList = new ArrayList<>();
        clazzObject = Clazz.builder()
                .id(1)
                .name("10-А")
                .academicYear(2018)
                .description("Some desc")
                .isActive(true)
                .build();
        clazzObject.getStudents().add(new Student());
        getAllClassesList.add(clazzObject);

        clazzList.add(
                Clazz.builder()
                .name("11-А")
                .description("Some desc")
                .academicYear(2019)
                .isActive(true).build()
        );
        List<Clazz> returnAftSaveClazzList = new ArrayList<>();
        List<ClassDTO> expectedDTOList = new ArrayList<>();
        returnAftSaveClazzList.add(new Clazz(2, "11-А", "Some desc", 2019, true));
        expectedDTOList.add(new ClassDTO(2, 2019, "11-А", "Some desc", true, 0));

        Mockito.when(classRepository.findAll()).thenReturn(getAllClassesList);
        Mockito.when(classRepository.saveAll(clazzList)).thenReturn(returnAftSaveClazzList);

        List<ClassDTO> result = classService.addNewYearClasses();
        assertEquals(expectedDTOList, result);
    }

    @Test
    public void testUpdateClassName(){
        String newNameWithLetter = classService.updateClassName("9-Б");
        String newNameWithoutLetter = classService.updateClassName("10");

        assertEquals("10-Б", newNameWithLetter);
        assertEquals("11", newNameWithoutLetter);
    }

    @Test
    public void testGetClassesBySubject(){
        clazzList.add(clazzObject);
        classDTOList.add(expectedClassDTO);

        Mockito.when(classRepository.findClassesBySubject(1)).thenReturn(clazzList);
        List<ClassDTO> result = classService.getClassesBySubject(1);
        assertEquals(classDTOList, result);
    }
}
