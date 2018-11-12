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

        int classId = teacherJournalDTO.getClassId();
        int teacherId = teacherJournalDTO.getTeacherId();
        int subjectId = teacherJournalDTO.getSubjectId();

        Clazz clazz = classRepository.findById(classId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        Subject subject = subjectRepository.findById(subjectId).get();

        //todo bk ++ use builder instead    -DONE
        classTeacherSubjectRepository.save(classTeacherSubject.builder()
                .clazz(clazz)
                .clazz_id(classId)
                .teacher(teacher)
                .teacher_id(teacherId)
                .subject(subject)
                .subject_id(subjectId)
                .isActive(isActive).build());
    }

}
