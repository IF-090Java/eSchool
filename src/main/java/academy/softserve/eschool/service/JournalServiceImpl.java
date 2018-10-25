package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @Override
    public List<JournalDTO> getSubjectsByTeacher(int idTeacher) {
        //return  classTeacherSubjectLinkRepository.findJournalsByTeacher(idTeacher);
        List<ClassTeacherSubjectLink> c = classTeacherSubjectLinkRepository.findJournalsByTeacher(idTeacher);
        System.out.println(c);
        return null;
    }
}
