package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.model.*;
import academy.softserve.eschool.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

@Service
public class ClassTeacherSubjectServiceImpl implements ClassTeacherSubjectService {

    @Autowired
    private ClassTeacherSubjectLinkRepository classTeacherSubjectRepository;

    @Autowired
    ClassRepository classRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public void saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive) {
        ClassTeacherSubjectLink classTeacherSubject = new ClassTeacherSubjectLink();

        int id_class = teacherJournalDTO.getClass_id();
        int id_teacher = teacherJournalDTO.getTeacher_id();
        int id_subject = teacherJournalDTO.getSubject_id();

        Clazz clazz = classRepository.findById(id_class).get();
        Teacher teacher = teacherRepository.findById(id_teacher).get();
        Subject subject = subjectRepository.findById(id_subject).get();


        classTeacherSubject.setClazz(clazz);
        classTeacherSubject.setClazz_id(id_class);
        classTeacherSubject.setTeacher(teacher);
        classTeacherSubject.setTeacher_id(id_teacher);
        classTeacherSubject.setSubject(subject);
        classTeacherSubject.setSubject_id(id_subject);
        classTeacherSubject.setActive(isActive);
        classTeacherSubjectRepository.save(classTeacherSubject);
    }
}
