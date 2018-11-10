package academy.softserve.eschool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.SubjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    @NonNull
    SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getAll() {
        List<Subject> subjectList = subjectRepository.findAll();
        return asSubjectDTOList(subjectList);
    }
    
    @Override
    public List<SubjectDTO> getSubjectsByClass(Integer classId) {
        List<Subject> subjectList = subjectRepository.findSubjectsByClass(classId);
        return asSubjectDTOList(subjectList);
        
    }

    @Override
    public SubjectDTO getSubjectById(int id) {
        Subject subject = subjectRepository.findById(id).orElse(null);

        return SubjectDTO.builder()
                .subjectName(subject.getName())
                .subjectDescription(subject.getDescription())
                .build();
    }

    @Override
    public List<SubjectDTO> getSubjectsByTeacher(int idTeacher) {
        List<Subject> subjectList = subjectRepository.findSubjectsByTeacher(idTeacher);
        return asSubjectDTOList(subjectList);
    }

    @Override
    public void addSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setName(subjectDTO.getSubjectName());
        subject.setDescription(subjectDTO.getSubjectDescription());
        subject = subjectRepository.save(subject);
    }

    @Override
    public void editSubject(int id, SubjectDTO subjectDTO) {
        subjectRepository.editSubject(id, subjectDTO.getSubjectName(), subjectDTO.getSubjectDescription());
    }
    
    private List<SubjectDTO> asSubjectDTOList(List<Subject> subjectList) {
        List<SubjectDTO> subjectDTOS = new ArrayList<>();

        for (Subject subject : subjectList) {
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
