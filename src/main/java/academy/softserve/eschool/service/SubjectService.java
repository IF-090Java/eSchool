package academy.softserve.eschool.service;

import java.util.List;

import academy.softserve.eschool.dto.SubjectDTO;

public interface SubjectService {
    List<SubjectDTO> getSubjectsByTeacher(int idTeacher);
    
    List<SubjectDTO> getAll();
    
    SubjectDTO getSubjectById(int id);
    
    void addSubject(SubjectDTO subjectDTO);
    
    void editSubject(int id, SubjectDTO subjectDTO);

    List<SubjectDTO> getSubjectsByClass(Integer classId);
}
