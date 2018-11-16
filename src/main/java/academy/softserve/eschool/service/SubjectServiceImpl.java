package academy.softserve.eschool.service;

import java.util.ArrayList;
import java.util.List;

import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.SubjectRepository;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;

import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
	
	@NonNull
	SubjectRepository subjectRepository;

	@Override
	public List<SubjectDTO> getAllSubjects() {
		List<Subject> subjectList = subjectRepository.findAll();
		return asSubjectDTOList(subjectList);
	}

	@Override
	public List<SubjectDTO> getSubjectsByTeacher(int idTeacher) {
		List<Subject> subjectList = subjectRepository.findSubjectsByTeacher(idTeacher);
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
	public SubjectDTO addSubject(SubjectDTO subjectDTO) {
		Subject savedSubject = subjectRepository.save(
				Subject.builder()
				.name(subjectDTO.getSubjectName())
				.description(subjectDTO.getSubjectDescription())
				.build()
		);
		return SubjectDTO.builder()
				.subjectId(savedSubject.getId())
				.subjectName(savedSubject.getName())
				.subjectDescription(savedSubject.getDescription())
				.build();
	}

	@Override
	public SubjectDTO editSubject(int id, SubjectDTO subjectDTO) {
		subjectRepository.editSubject(id, subjectDTO.getSubjectName(), subjectDTO.getSubjectDescription());
		Subject updatedSubject = subjectRepository.findById(id).orElse(null);
		return SubjectDTO.builder()
				.subjectId(updatedSubject.getId())
				.subjectName(updatedSubject.getName())
				.subjectDescription(updatedSubject.getDescription())
				.build();
	}

	private List<SubjectDTO> asSubjectDTOList(List<Subject> subjectList) {
		List<SubjectDTO> subjectDTOs = new ArrayList<>();
		for (Subject subject : subjectList) {
			SubjectDTO subjectDTO = SubjectDTO.builder()
					.subjectId(subject.getId())
					.subjectName(subject.getName())
					.subjectDescription(subject.getDescription())
					.build();
			subjectDTOs.add(subjectDTO);
		}
		return subjectDTOs;
	}

}
