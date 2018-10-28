package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	SubjectRepository subjectRepository;

	@Override
	public List<SubjectDTO> getAll() {
		List<Subject> subjectList = subjectRepository.findAll();

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

}
