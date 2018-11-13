package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.File;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.repository.FileRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {

    @NonNull
    private ClassTeacherSubjectLinkRepository classTeacherSubjectLinkRepository;
    @NonNull
    private StudentRepository studentRepository;
    @NonNull
    private LessonRepository lessonRepository;
    @NonNull
    private FileRepository fileRepository;


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
    public List<JournalDTO> getActiveJournalsByTeacher(int idTeacher) {
        List<ClassTeacherSubjectLink> listCTS = classTeacherSubjectLinkRepository.findActiveJournalsByTeacher(idTeacher);
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
        List<Map<String,Object>>  list = studentRepository.findJournal(idSubject,idClass);
         List<JournalMarkDTO> JMDto = new ArrayList<>();
         Map<Integer,String> students = new HashMap<>();
         for(Map<String,Object> map: list){
             String name = map.get("first_name")+" "+map.get("last_name");
             students.put((Integer) map.get("id_student"),name);
        }
        for(Map.Entry<Integer, String> entry : students.entrySet()) {
            JournalMarkDTO dto = JournalMarkDTO.builder()
                    .idStudent(entry.getKey())
                    .studentFullName(entry.getValue())
                    .marks(new ArrayList<>())
                    .build();
            JMDto.add(dto);
        }
        for (JournalMarkDTO jm : JMDto){
            for (Map<String,Object> object: list){
                if(jm.getIdStudent()==(Integer)object.get("id_student")){
                    MarkDescriptionDTO desc = MarkDescriptionDTO.builder()
                            .idLesson((Integer)object.get("id"))
                            .mark((Byte)object.get("mark"))
                            .dateMark((Date)object.get("date"))
                            .typeMark((String)object.get("mark_type"))
                            .note((String)object.get("note"))
                            .build();
                    jm.getMarks().add(desc);
                }
            }
        }
        return JMDto;
    }

    @Override
    public List<HomeworkDTO> getHomework(int idSubject, int idClass) {
        List<Lesson> list = lessonRepository.findHomework(idSubject,idClass);
        List<HomeworkDTO> homeworkDTOS = new ArrayList<>();
        for(Lesson lesson: list){
            HomeworkDTO dto = HomeworkDTO.builder()
                    .idLesson(lesson.getId())
                    .date(lesson.getDate())
                    .homework(lesson.getHometask())
                    .build();
            if(lesson.getFile()!=null){
                dto.setFileName(lesson.getFile().getFileName());
            }
            else dto.setFileName(null);
            homeworkDTOS.add(dto);
        }
        return homeworkDTOS;
    }

    @Override
    public HomeworkFileDTO getFile(int idLesson) {
        Lesson lesson = lessonRepository.findFile(idLesson);
        HomeworkFileDTO homeworkFileDTO = HomeworkFileDTO.builder()
                .idLesson(idLesson)
                .fileData(lesson.getFile().getFile())
                .fileName(lesson.getFile().getFileName())
                .fileType(lesson.getFile().getFileType())
                .homework(lesson.getHometask())
                .build();
        return homeworkFileDTO;
    }

    @Override
    public void saveHomework(HomeworkFileDTO fileDTO) {
        Lesson lesson = lessonRepository.findById(fileDTO.getIdLesson()).orElse(null);
        if(lesson!=null){
            if(fileDTO.getFileName()!=null){
                File file = File.builder()
                        .file(fileDTO.getFileData())
                        .fileType(fileDTO.getFileType())
                        .fileName(fileDTO.getFileName())
                        .build();
                File savedFile = fileRepository.save(file);
                lessonRepository.saveHomeWork(fileDTO.getHomework(),savedFile.getId(),fileDTO.getIdLesson());
            }
            else{
                if(lesson.getFile()==null) {
                    lessonRepository.saveHomeWork(fileDTO.getHomework(), null, fileDTO.getIdLesson());
                }
                else {
                    lessonRepository.saveHomeWork(fileDTO.getHomework(), lesson.getFile().getId(), fileDTO.getIdLesson());
                }
            }
        }

    }
}
