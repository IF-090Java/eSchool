package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getSubjectsByTeacher(int idTeacher);
}
