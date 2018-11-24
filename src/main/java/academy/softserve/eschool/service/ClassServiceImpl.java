package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
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
    public List<ClassDTO> getAllClasses() {
        List<Clazz> clazzList = classRepository.findAll();
        return clazzList.stream().map((i) -> ClassDTO.builder()
                .id(i.getId())
                .className(i.getName())
                .classDescription(i.getDescription())
                .classYear(i.getAcademicYear())
                .isActive(i.isActive())
                .numOfStudents(i.getStudents().size()).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ClassDTO findClassById(int id) {
        Clazz clazz = classRepository.getOne(id);
        return ClassDTO.builder()
                .className(clazz.getName())
                .classDescription(clazz.getDescription())
                .classYear(clazz.getAcademicYear())
                .isActive(clazz.isActive()).build();
    }

    @Override
    public ClassDTO saveClass(ClassDTO classDTO) {
        Clazz savedClass = classRepository.save(
            Clazz.builder()
                .name(classDTO.getClassName())
                .description(classDTO.getClassDescription())
                .academicYear(classDTO.getClassYear())
                .isActive(classDTO.getIsActive()).build()
            );
        return ClassDTO.builder()
                .id(savedClass.getId())
                .className(savedClass.getName())
                .classDescription(savedClass.getDescription())
                .classYear(savedClass.getAcademicYear())
                .isActive(savedClass.isActive()).build();
    }

    @Override
    public ClassDTO updateClass(int id, ClassDTO classDTO) {
        Clazz clazz = classRepository.getOne(id);
        clazz.setName(classDTO.getClassName());
        clazz.setDescription(classDTO.getClassDescription());
        clazz.setAcademicYear(classDTO.getClassYear());
        clazz.setActive(classDTO.getIsActive());

        Clazz updatedClass = classRepository.save(clazz);
        return ClassDTO.builder()
                .id(updatedClass.getId())
                .className(updatedClass.getName())
                .classDescription(updatedClass.getDescription())
                .classYear(updatedClass.getAcademicYear())
                .isActive(updatedClass.isActive())
                .numOfStudents(updatedClass.getStudents().size()).build();
    }

    @Override
    public List<ClassDTO> addNewYearClasses() {
        List<ClassDTO> allClasses = getAllClasses();
        List<Clazz> newYearClasses = new ArrayList<>();

        for (ClassDTO classDTO : allClasses){
            if (classDTO.getIsActive() == true && classDTO.getNumOfStudents() > 0 && !classDTO.getClassName().startsWith("11")){
                newYearClasses.add(
                Clazz.builder()
                        .name(updateClassName(classDTO.getClassName()))
                        .description(classDTO.getClassDescription())
                        .academicYear(classDTO.getClassYear()+1)
                        .isActive(classDTO.getIsActive()).build()
                );
            }
        }
        List<Clazz> classes = classRepository.saveAll(newYearClasses);
        return classes.stream().map(c -> ClassDTO.builder()
                .id(c.getId())
                .className(c.getName())
                .classDescription(c.getDescription())
                .classYear(c.getAcademicYear())
                .isActive(c.isActive()).build()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void updateClassStatusById(List<NYTransitionDTO> transitionDTOS, boolean status) {
        for (NYTransitionDTO dto: transitionDTOS){
            classRepository.updateClassStatusById(dto.getOldClassId(), status);
        }
    }

    public String updateClassName(String className) {
        String[] classNameParts = className.split("-");
        if (classNameParts.length>1){
            int classNum = Integer.parseInt(classNameParts[0])+1;
            return classNum+"-"+classNameParts[1];
        } else {
            int classNum = Integer.parseInt(classNameParts[0])+1;
            return String.valueOf(classNum);
        }
    }

    @Override
    public List<ClassDTO> getClassesBySubject(Integer subjectId) {
        List<Clazz> clazzList = classRepository.findClassesBySubject(subjectId);
        return clazzList.stream().map((i) -> ClassDTO.builder()
                .id(i.getId())
                .className(i.getName())
                .classDescription(i.getDescription())
                .classYear(i.getAcademicYear())
                .isActive(i.isActive())
                .numOfStudents(i.getStudents().size()).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}
