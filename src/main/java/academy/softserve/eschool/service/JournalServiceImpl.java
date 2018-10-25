package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @Override
    public List<JournalDTO> getSubjectsByTeacher(int idTeacher) {
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
}
