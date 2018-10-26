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
         Map<Integer,String> students = new HashMap<>();
         for(Object[] a: list){
             String name = a[1]+" "+a[2];
             students.put((Integer)a[0],name);
        }
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            JournalMarkDTO dto = JournalMarkDTO.builder()
                    .idStudent(entry.getKey())
                    .studentFullName(entry.getValue())
                    .marks(new ArrayList<>())
                    .build();
            JMDto.add(dto);
        }
        for (JournalMarkDTO jm : JMDto){
            for (Object[] object: list){
                if(jm.getIdStudent()==(Integer)object[0]){
                    MarkDescriptionDTO desc = MarkDescriptionDTO.builder()
                            .idLesson((Integer)object[3])
                            .mark((Byte)object[4])
                            .dateMark((Date)object[5])
                            .typeMark((String)object[6])
                            .note((String)object[7])
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
