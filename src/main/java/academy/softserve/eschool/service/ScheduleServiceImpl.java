package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public ScheduleDTO getScheduleByClassId(int id_class) {
        List<Map<String, Object>> monday = lessonRepository.scheduleByClassId(0, id_class);
        List<Map<String, Object>> tuesday = lessonRepository.scheduleByClassId(1, id_class);
        List<Map<String, Object>> wednesday = lessonRepository.scheduleByClassId(2, id_class);
        List<Map<String, Object>> thursday = lessonRepository.scheduleByClassId(3, id_class);
        List<Map<String, Object>> friday = lessonRepository.scheduleByClassId(4, id_class);

        Clazz clazz = classRepository.findById(id_class).get();

        return ScheduleDTO.builder()
                .startOfSemester(null)
                .endOfSemester(null)
                .className(new ClassDTO(clazz.getId(), clazz.getAcademicYear(), clazz.getName(),
                        clazz.getDescription(), String.valueOf(clazz.isActive())))
                .mondaySubjects(convertFromObject(monday))
                .tuesdaySubjects(convertFromObject(tuesday))
                .wednesdaySubjects(convertFromObject(wednesday))
                .thursdaySubjects(convertFromObject(thursday))
                .fridaySubjects(convertFromObject(friday)).build();
    }

    @Override
    public void saveSchedule(ScheduleDTO scheduleDTO) {

        LocalDate start = scheduleDTO.getStartOfSemester();
        LocalDate end = scheduleDTO.getEndOfSemester();
        List<SubjectDTO> monday = scheduleDTO.getMondaySubjects();
        List<SubjectDTO> tuesday = scheduleDTO.getTuesdaySubjects();
        List<SubjectDTO> wednesday = scheduleDTO.getWednesdaySubjects();
        List<SubjectDTO> thursday = scheduleDTO.getThursdaySubjects();
        List<SubjectDTO> friday = scheduleDTO.getFridaySubjects();

        int id_class = scheduleDTO.getClassName().getId();
        Clazz clazz = classRepository.findById(id_class).get();

        saveFunction(monday, start, end, DayOfWeek.MONDAY, clazz);
        saveFunction(tuesday, start, end, DayOfWeek.TUESDAY, clazz);
        saveFunction(wednesday, start, end, DayOfWeek.THURSDAY, clazz);
        saveFunction(thursday, start, end, DayOfWeek.THURSDAY, clazz);
        saveFunction(friday, start, end, DayOfWeek.FRIDAY, clazz);
    }

    //this is a method to save schedule for a particular day
    public void saveFunction(List<SubjectDTO> list, LocalDate start, LocalDate end, DayOfWeek dayOfWeek, Clazz clazz)
    {
        for (int i = 0; i < list.size(); i ++) {
            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                DayOfWeek dow = date.getDayOfWeek();
                if (dow == dayOfWeek) {
                    lessonRepository.save(
                            Lesson.builder()
                                    .lessonNumber((byte) (i + 1))
                                    .date(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                                    .hometask(null)
                                    .markType(null)
                                    .file(null)
                                    .clazz(clazz)
                                    .subject(subjectRepository.getOne(list.get(i).getSubjectId())).build()
                    );
                }
            }
        }
    }

    //this is a method to convert List<Map<String, Object>> into SubjectDTO
    public List<SubjectDTO> convertFromObject(List<Map<String, Object>> somelist)
    {
        List<SubjectDTO> list = somelist.stream().map((obj) -> {
            int id = (int)obj.get("id");
            String name = (String) obj.get("name");
            String description = (String)obj.get("description");
            return new SubjectDTO(id, name, description);
        })
                .collect(Collectors.toList());
        return list;
    }

}
