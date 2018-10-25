package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getSubjectsByTeacher(int idTeacher) {
       List<Subject> subjectList = subjectRepository.findSubjectsByTeacher(idTeacher);

        List<SubjectDTO> subjectDTOS = new ArrayList<>();

        for (Subject subject : subjectList){
            SubjectDTO subjectDTO = SubjectDTO.builder()
                    .subjectId(subject.getId())
                    .subjectName(subject.getName())
                    .subjectDescription(subject.getDescription())
                    .build();
            subjectDTOS.add(subjectDTO);
        }
        return subjectDTOS;
    }
}
