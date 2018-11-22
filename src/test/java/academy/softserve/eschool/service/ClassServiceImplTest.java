package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

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

    Clazz savedClazz;
    Clazz classToSave;
    ClassDTO expectedDTO;

    @Before
    public void init(){
        classId = 1;
        className = "10-А";
        academicYear = 2018;
        classDescription = "Some new class";
        isActive = true;
        numOfStudents = 0;

        savedClazz = Clazz.builder()
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

        expectedDTO = ClassDTO.builder()
                .id(classId)
                .className(className)
                .classDescription(classDescription)
                .classYear(academicYear)
                .isActive(isActive)
                .numOfStudents(numOfStudents).build();
    }

    @Test
    public void testSaveClass(){
        Mockito.when(classRepository.save(classToSave)).thenReturn(savedClazz);
        ClassDTO result = classService.saveClass(new ClassDTO(classId, academicYear, className, classDescription, isActive, numOfStudents));
        assertEquals(result, expectedDTO);
    }
}
