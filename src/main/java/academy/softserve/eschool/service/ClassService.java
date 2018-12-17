package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;

import java.util.List;

public interface ClassService {
    List<ClassDTO> getAllClasses();
    ClassDTO findClassById(int id);
    List<ClassDTO> getClassesBySubject(Integer subjectId);
    ClassDTO saveClass(ClassDTO classDTO);
    ClassDTO updateClass(int id, ClassDTO classDTO);
    List<ClassDTO> addNewYearClasses(List<ClassDTO> classDTOS);
    void updateClassStatusById(List<NYTransitionDTO> transitionDTOS, boolean status);
}
