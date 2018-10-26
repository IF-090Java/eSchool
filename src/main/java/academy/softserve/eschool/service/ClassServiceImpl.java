package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired ClassRepository classRepository;

    @Override
    public List<ClassDTO> findClassesByStatus(boolean status) {
        List<Clazz> clazzList = classRepository.findClassByActiveStatus(status);

        return clazzList.stream().map((i) -> ClassDTO.builder()
                .id(i.getId())
                .className(i.getName())
                .classDescription(i.getDescription())
                .classYear(i.getAcademicYear())
                .isActive(Boolean.toString(i.isActive())).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ClassDTO findClassById(int id) {
        Clazz clazz = classRepository.findById(id).orElse(null);
        return ClassDTO.builder()
                .className(clazz.getName())
                .classDescription(clazz.getDescription())
                .classYear(clazz.getAcademicYear())
                .isActive(Boolean.toString(clazz.isActive())).build();
    }

    @Override
    public void saveClass(ClassDTO classDTO) {
        Boolean isActive = Boolean.valueOf(classDTO.getIsActive());
        classRepository.save(
        Clazz.builder()
                .name(classDTO.getClassName())
                .description(classDTO.getClassDescription())
                .academicYear(classDTO.getClassYear())
                .isActive(isActive).build()
        );
    }

    @Override
    public void updateClass(int id, ClassDTO classDTO) {
        classRepository.updateClass(id, classDTO.getClassName(), classDTO.getClassYear(), classDTO.getClassDescription(), Boolean.valueOf(classDTO.getIsActive()));
    }

    @Override
    public void addNewYearClasses() {
        List<ClassDTO> prevYearClasses = findClassesByStatus(true);
        prevYearClasses.stream().forEach(c -> c.setClassYear(c.getClassYear()+1));
        prevYearClasses.stream().forEach(c -> c.setClassName(updateClassName(c.getClassName())));
        prevYearClasses.stream().forEach(c -> saveClass(c));
    }

    @Override
    public List<ClassDTO> getActiveClassesWithStudents() {
        List<Clazz> aClasses = classRepository.getActiveClassesWithStudents();
        return aClasses.stream().map(i -> ClassDTO.builder()
                .id(i.getId())
                .className(i.getName())
                .classYear(i.getAcademicYear())
                .classDescription(i.getDescription()).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String updateClassName(String className) {
        String[] classNameParts = className.split("-");
        if (classNameParts.length>1){
            int classNum = Integer.parseInt(classNameParts[0])+1;
            return String.valueOf(classNum)+"-"+classNameParts[1];
        } else {
            int classNum = Integer.parseInt(classNameParts[0])+1;
            return String.valueOf(classNum);
        }
    }


}
