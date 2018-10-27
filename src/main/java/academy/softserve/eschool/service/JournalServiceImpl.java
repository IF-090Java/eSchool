package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Override
    public List<JournalDTO> getJournalsByTeacher(int idTeacher) {
        List<ClassTeacherSubjectLink> listCTS = classTeacherSubjectLinkRepository.findJournalsByTeacher(idTeacher);
        List<JournalDTO> listDTO = new ArrayList<>();
        for(ClassTeacherSubjectLink link: listCTS){
            JournalDTO dto = JournalDTO.builder()
                    .idClass(link.getClazz().getId())
                    .idSubject(link.getSubject().getId())
                    .className(link.getClazz().getName())
                    .subjectName(link.getSubject().getName())
                    .academicYear(link.getClazz().getAcademicYear())
                    .build();
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    public List<JournalDTO> getJournals() {
        List<ClassTeacherSubjectLink> listCTS = classTeacherSubjectLinkRepository.findJournals();
        List<JournalDTO> listDTO = new ArrayList<>();
        for(ClassTeacherSubjectLink link: listCTS){
            JournalDTO dto = JournalDTO.builder()
                    .idClass(link.getClazz().getId())
                    .idSubject(link.getSubject().getId())
                    .className(link.getClazz().getName())
                    .subjectName(link.getSubject().getName())
                    .academicYear(link.getClazz().getAcademicYear())
                    .build();
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    public List<JournalMarkDTO> getJournal(int idSubject, int idClass) {
         List<Object[]> list = studentRepository.findJournal(idSubject,idClass);
         List<JournalMarkDTO> JMDto = new ArrayList<>();
         Set<String> students = new HashSet<>();
         for(Object[] a: list){
             students.add((String)a[1]+" "+(String)a[0]);
        }
        for (String a: students){
            JournalMarkDTO dto = JournalMarkDTO.builder()
                    .studentFullName(a)
                    .marks(new ArrayList<>())
                    .build();
            JMDto.add(dto);
        }
        for (JournalMarkDTO jm : JMDto){
            for (Object[] object: list){
                if(jm.getStudentFullName().equals((String)object[1]+" "+(String)object[0])){
                    MarkDescriptionDTO desc = MarkDescriptionDTO.builder()
                            .mark((Byte)object[2])
                            .dateMark((Date)object[3])
                            .typeMark((String)object[4])
                            .build();
                    jm.getMarks().add(desc);
                }
            }
        }
        return JMDto;
    }

    @Override
    public List<HomeworkDTO> getHomework(int idSubject, int idClass) {
        List<Lesson> list = lessonRepository.findHomework(idSubject,idClass);
        List<HomeworkDTO> homeworkDTOS = new ArrayList<>();
        for(Lesson lesson: list){
            HomeworkDTO dto = HomeworkDTO.builder()
                    .date(lesson.getDate())
                    .file(lesson.getFile())
                    .homework(lesson.getHometask())
                    .build();
            homeworkDTOS.add(dto);
        }
        return homeworkDTOS;
    }
}
