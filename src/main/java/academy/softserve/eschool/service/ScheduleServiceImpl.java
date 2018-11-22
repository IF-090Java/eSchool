package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.SubjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements the interface {@link ScheduleService}
 * and contains four methods:
 * <ul>
 *     <li>
 *     the first and second - get/save data from/into the DB
 *     </li>
 *     <li>
 *     the third and fourth - help for get/save data methods
 *     </li>
 * </ul>
 * in lesson table.
 *
 * @author Mariana Vorotniak
 */
@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService{

    @NonNull
    LessonRepository lessonRepository;
    @NonNull
    ClassRepository classRepository;
    @NonNull
    SubjectRepository subjectRepository;
    /**
     * Returns an object of {@link ScheduleDTO} that describes a schedule of a class with specified id for current week
     * @param classId   id of the class for which is the schedule taken from DB
     * @return          Object of {@link ScheduleDTO}
     */
    @Override
    public ScheduleDTO getScheduleByClassId(int classId) {
        List<Map<String, Object>> lessons = lessonRepository.scheduleByClassId(classId);
        Clazz clazz = classRepository.findById(classId).get();

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
    /**
     * Saves an object of {@link ScheduleDTO} into the lesson table
     * @param scheduleDTO   object that will be saved
     */
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

    /**
     * This method saves schedule for a particular day. It helps the {@link #saveSchedule(ScheduleDTO)} method to save data
     * @param list      list of {@link SubjectDTO} objects that represents the lessons of a particular day
     * @param start     date of start of the semester
     * @param end       date of end of the semester
     * @param dayOfWeek day of week for which are the lessons saved
     * @param clazz     class for which is the schedule saved
     */
    public List<Lesson> saveFunction(List<SubjectDTO> list, LocalDate start, LocalDate end, DayOfWeek dayOfWeek, Clazz clazz)
    {
        List<Subject> resultList = new ArrayList<>();
        if (list.size() != 0) {
            List<Integer> listOfIds = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listOfIds.add(list.get(i).getSubjectId());
            }
            List<Subject> listOfSubjects = subjectRepository.findAll();
            for (int i = 0; i < listOfIds.size(); i ++)
            {
                for (int j = 0; j < listOfSubjects.size(); j ++) {
                    if (listOfSubjects.get(j).getId() == listOfIds.get(i))
                        resultList.add(listOfSubjects.get(j));
                }
            }
        }
        LocalDate dateAfterEnd = end.plusDays(1);
        List<Lesson> listOfLessons = new ArrayList<>();
        for (int i = 0; i < list.size(); i ++) {
            for (LocalDate date = start; date.isBefore(dateAfterEnd); date = date.plusDays(1)) {
                DayOfWeek dow = date.getDayOfWeek();
                if (dow == dayOfWeek) {
                    listOfLessons.add(
                            Lesson.builder()
                                    .lessonNumber((byte) (i + 1))
                                    .date(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                                    .hometask("")
                                    .markType(null)
                                    .file(null)
                                    .clazz(clazz)
                                    .subject(resultList.get(i)).build()
                    );
                }
            }
        }
        lessonRepository.saveAll(listOfLessons);
        return listOfLessons;
    }

    /**
     * This method converts List<Map<String, Object>> into a list of {@link SubjectDTO} and filters it by day of week.
     * It helps the {@link #getScheduleByClassId(int)} method to save data.
     * @param someList     list that needs to be converted into List<SubjectDTO> and filtered by day of week
     * @param dayOfWeek    day of week for what will be returned the list
     * @return             List of {@link SubjectDTO} filtered by {@param dayOfWeek} that represents a schedule for a particular day
     */
    public List<SubjectDTO> convertFromObject(List<Map<String, Object>> someList, DayOfWeek dayOfWeek)
    {
        List<LocalDate> listDate = someList.stream().map((obj) -> {
            LocalDate date = LocalDate.parse((CharSequence) obj.get("date"));
            return date;
        })
                .collect(Collectors.toList());

        List<SubjectDTO> list = someList.stream().map((obj) -> {
                    int id = Integer.valueOf((String) obj.get("id"));
                    String name = (String) obj.get("name");
                    String description = (String) obj.get("description");
                    return new SubjectDTO(id, name, description);

                })
                        .collect(Collectors.toList());
        for (int i = 0; i < listDate.size(); i++) {
            if (listDate.get(i).getDayOfWeek() != dayOfWeek) {
                list.remove(i);
            }
        }
        return list;
    }
}