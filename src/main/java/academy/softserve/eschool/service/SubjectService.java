package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getSubjectsByTeacher(int idTeacher);
    
    List<SubjectDTO> getAll();
    
    SubjectDTO getSubjectById(int id);
    
    void addSubject(SubjectDTO subjectDTO);
    
    void editSubject(int id, SubjectDTO subjectDTO);
}
