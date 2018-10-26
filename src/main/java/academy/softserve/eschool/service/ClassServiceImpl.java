package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired ClassRepository classRepository;

    @Override
    public List<ClassDTO> findClassesByStatus(boolean status) {
        List<Clazz> clazzList = classRepository.findClassByStatus(status);

        List<ClassDTO> classDTOS = new ArrayList<>();

        for (Clazz clazz : clazzList){
            clazz.isActive();
            ClassDTO classDTO = new ClassDTO();
            classDTO.setId(clazz.getId());
            classDTO.setClassName(clazz.getName());
            classDTO.setClassYear(clazz.getAcademicYear());
            classDTO.setClassDescription(clazz.getDescription());
            classDTO.setIsActive(Boolean.toString(clazz.isActive()));
            classDTOS.add(classDTO);
        }
        return classDTOS;
    }

    @Override
    public ClassDTO findClassById(int id) {
        Clazz clazz = classRepository.findById(id).orElse(null);
  
        return ClassDTO.builder()
                .className(clazz.getName())
                .classDescription(clazz.getDescription())
                .classYear(clazz.getAcademicYear())
                .isActive(Boolean.toString(clazz.isActive())).build();
//
//        classDTO = new ClassDTO();
//        classDTO.setClassName(clazz.getName());
//        classDTO.setClassYear(clazz.getAcademicYear());
//        classDTO.setClassDescription(clazz.getDescription());
//        classDTO.setIsActive(Boolean.toString(clazz.isActive()));
//        return classDTO;
    }

    @Override
    public void saveClass(ClassDTO classDTO) {
        Clazz clazz = new Clazz();
        clazz.setName(classDTO.getClassName());
        clazz.setAcademicYear(classDTO.getClassYear());
        clazz.setDescription(classDTO.getClassDescription());
        clazz.setActive(Boolean.valueOf(classDTO.getIsActive()));
        classRepository.save(clazz);
    }

    @Override
    public void updateClass(int id, ClassDTO classDTO) {
        classRepository.updateClass(id, classDTO.getClassName(), classDTO.getClassYear(), classDTO.getClassDescription(), Boolean.valueOf(classDTO.getIsActive()));
    }
}
