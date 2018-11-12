package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
        //todo bk refactor it by doing just single call to db to get the data
        List<Map<String, Object>> lessons = lessonRepository.scheduleByClassId(id_class);

        Clazz clazz = classRepository.findById(id_class).get();

        return ScheduleDTO.builder()
                .startOfSemester(null)
                .endOfSemester(null)
                .className(new ClassDTO(clazz.getId(), clazz.getAcademicYear(), clazz.getName(),
                        clazz.getDescription(), clazz.isActive(), clazz.getStudents().size()))
                .mondaySubjects(convertFromObject(lessons, DayOfWeek.MONDAY))
                .tuesdaySubjects(convertFromObject(lessons, DayOfWeek.TUESDAY))
                .wednesdaySubjects(convertFromObject(lessons, DayOfWeek.WEDNESDAY))
                .thursdaySubjects(convertFromObject(lessons, DayOfWeek.THURSDAY))
                .fridaySubjects(convertFromObject(lessons, DayOfWeek.FRIDAY)).build();
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
        saveFunction(wednesday, start, end, DayOfWeek.WEDNESDAY, clazz);
        saveFunction(thursday, start, end, DayOfWeek.THURSDAY, clazz);
        saveFunction(friday, start, end, DayOfWeek.FRIDAY, clazz);
    }

    //this is a method to save schedule for a particular day
    public void saveFunction(List<SubjectDTO> list, LocalDate start, LocalDate end, DayOfWeek dayOfWeek, Clazz clazz)
    {
        List<Subject> listOfSubjects = new ArrayList<>();
        if (list.size() != 0) {
            List<Integer> listOfIds = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listOfIds.add(list.get(i).getSubjectId());
            }
            listOfSubjects = subjectRepository.findSubjectsByIds(listOfIds);
        }
        List<Lesson> listOfLessons = new ArrayList<>();
        for (int i = 0; i < list.size(); i ++) {
            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                DayOfWeek dow = date.getDayOfWeek();
                if (dow == dayOfWeek) {
                    //todo bk !!!!!!! Never do it again - calling repository method in loop. Just prepare all required data and save it once
                    listOfLessons.add(
                            Lesson.builder()
                                    .lessonNumber((byte) (i + 1))
                                    .date(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                                    .hometask(null)
                                    .markType(null)
                                    .file(null)
                                    .clazz(clazz)
                                    .subject(listOfSubjects.get(i)).build()
                    );
                }
            }
        }
        lessonRepository.saveAll(listOfLessons);

    }

    //this is a method to convert List<Map<String, Object>> into SubjectDTO
    public List<SubjectDTO> convertFromObject(List<Map<String, Object>> somelist, DayOfWeek dayOfWeek)
    {
        List<LocalDate> listdate = somelist.stream().map((obj) -> {
            LocalDate date = (LocalDate) obj.get("date");
            return date;
        })
                .collect(Collectors.toList());

        List<SubjectDTO> list = new ArrayList<>();

        for (int i = 0; i < listdate.size(); i++) {
            if (listdate.get(i).getDayOfWeek() == dayOfWeek) {
                list = somelist.stream().map((obj) -> {
                    int id = (int) obj.get("id");
                    String name = (String) obj.get("name");
                    String description = (String) obj.get("description");
                    return new SubjectDTO(id, name, description);
                })
                        .collect(Collectors.toList());
            }
        }
        return list;
    }

}
