package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Mark;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @Autowired
    StudentRepository studentRepository;
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
        List<Student> listStudents = studentRepository.findJournal(idSubject,idClass);
            List<JournalMarkDTO> listDTO = new ArrayList<>();
            for(Student link: listStudents){
                System.out.println(link.getMarks());
                /*List<MarkDescriptionDTO> listMarks = new ArrayList<>();
                for(Mark mark: link.getMarks()) {
                    MarkDescriptionDTO dto = MarkDescriptionDTO.builder()
                            .mark(mark.getMark())
                            .dateMark(mark.getLesson().getDate())
                            .typeMark(mark.getLesson().getMarkType().toString())
                            .build();
                    listMarks.add(dto);
                }
                String fullName = link.getFirstName()+" "+link.getLastName();
                System.out.println(fullName);
                JournalMarkDTO markDTO = JournalMarkDTO.builder()
                        .studentFullName(fullName)
                        .marks(listMarks)
                        .build();
                listDTO.add(markDTO);*/
            }
            return listDTO;
    }
}
