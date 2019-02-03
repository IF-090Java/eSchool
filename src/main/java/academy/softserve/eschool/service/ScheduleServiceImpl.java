package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.*;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.MarkType;
import academy.softserve.eschool.model.Subject;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.MarkTypeRepository;
import academy.softserve.eschool.repository.SubjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @NonNull
    LessonRepository lessonRepository;
    @NonNull
    ClassRepository classRepository;
    @NonNull
    SubjectRepository subjectRepository;
    @NonNull
    MarkTypeRepository markTypeRepository;
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
                .fridaySubjects(convertFromObject(lessons, DayOfWeek.FRIDAY))
                .saturdaySubjects(convertFromObject(lessons, DayOfWeek.SATURDAY)).build();
    }
    /**
     * Saves an object of {@link ScheduleDTO} into the lesson table
     * @param scheduleDTO   object that will be saved
     */
    @Override
    public void saveSchedule(ScheduleDTO scheduleDTO) {

        LocalDate start = scheduleDTO.getStartOfSemester();
        LocalDate end = scheduleDTO.getEndOfSemester();
        List<LessonDTO> monday = scheduleDTO.getMondaySubjects();
        List<LessonDTO> tuesday = scheduleDTO.getTuesdaySubjects();
        List<LessonDTO> wednesday = scheduleDTO.getWednesdaySubjects();
        List<LessonDTO> thursday = scheduleDTO.getThursdaySubjects();
        List<LessonDTO> friday = scheduleDTO.getFridaySubjects();
        List<LessonDTO> saturday = scheduleDTO.getSaturdaySubjects();

        int id_class = scheduleDTO.getClassName().getId();
        Clazz clazz = classRepository.findById(id_class).get();

        saveFunction(monday, start, end, DayOfWeek.MONDAY, clazz);
        saveFunction(tuesday, start, end, DayOfWeek.TUESDAY, clazz);
        saveFunction(wednesday, start, end, DayOfWeek.WEDNESDAY, clazz);
        saveFunction(thursday, start, end, DayOfWeek.THURSDAY, clazz);
        saveFunction(friday, start, end, DayOfWeek.FRIDAY, clazz);
        saveFunction(saturday, start, end, DayOfWeek.SATURDAY, clazz);
    }

    /**
     * This method saves schedule for a particular day. It helps the {@link #saveSchedule(ScheduleDTO)} method to save data
     * @param list      list of {@link LessonDTO} objects that represents the lessons of a particular day
     * @param start     date of start of the semester
     * @param end       date of end of the semester
     * @param dayOfWeek day of week for which are the lessons saved
     * @param clazz     class for which is the schedule saved
     */
    public List<Lesson> saveFunction(List<LessonDTO> list, LocalDate start, LocalDate end, DayOfWeek dayOfWeek, Clazz clazz)
    {
        List<Subject> resultList = new ArrayList<>();
        if (list.size() != 0) {
            List<Integer> listOfIds = new ArrayList<>();
            for (LessonDTO lessonDTO : list) {
                listOfIds.add(lessonDTO.getSubjectId());
            }
            List<Subject> listOfSubjects = subjectRepository.findAll();
            for (int id : listOfIds) {
                for (Subject subject : listOfSubjects) {
                    if (subject.getId() == id)
                        resultList.add(subject);
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
                                    .lessonNumber(list.get(i).getLessonNumber())
                                    .date(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                                    .hometask("")
                                    .markType(markTypeRepository.getOne(1))
                                    .file(null)
                                    .clazz(clazz)
                                    .subject(resultList.get(i)).build()
                    );
                }
            }
        }
        lessonRepository.saveAll(listOfLessons);
        logger.info("Lessons' quantity for [{}] is [{}], semester's bound [{}]-[{}], class [{}]",
                 dayOfWeek, listOfLessons.size(), start, end, clazz.getId());
        return listOfLessons;
    }

    /**
     * This method converts List<Map<String, Object>> into a list of {@link LessonDTO} and filters it by day of week.
     * It helps the {@link #getScheduleByClassId(int)} method to save data.
     * @param someList     list that needs to be converted into List<LessonDTO> and filtered by day of week
     * @param dayOfWeek    day of week for what will be returned the list
     * @return             List of {@link LessonDTO} filtered by {@param dayOfWeek} that represents a schedule for a particular day
     */
    public List<LessonDTO> convertFromObject(List<Map<String, Object>> someList, DayOfWeek dayOfWeek)
    {
        List<LocalDate> listDate = someList.stream().map((obj) -> {
            LocalDate date = LocalDate.parse(obj.get("date").toString());
            return date;
        })
                .collect(Collectors.toList());

        logger.debug("listDate size = {}", listDate.size());
        List<LessonDTO> list = someList.stream().map((obj) -> {
                    byte lessonNum = (Byte) obj.get("lesson_number");
                    int id = (Integer)obj.get("id");
                    String name = (String) obj.get("name");
                    String description = (String) obj.get("description");
                    return new LessonDTO(lessonNum, id, name, description);

                })
                        .collect(Collectors.toList());

        logger.debug("list size = {}" , list.size());
            for (int i = 0; i < listDate.size(); i++) {
                if (listDate.get(i).getDayOfWeek() != dayOfWeek) {
                    list.set(i, null);
                }
            }
            list.removeAll(Collections.singleton(null));

        logger.debug("Lessons' quantity for [{}] is [{}]", dayOfWeek, list.size());
        return list;
    }

    public Map<LocalDate, List<LessonDTO>> getYearScheduleByClassId(int classId){
        Map<LocalDate, List<LessonDTO>> ret = new HashMap<>();
        List<Map<String, Object>> lessons = lessonRepository.allScheduleByClassId(classId);
        for (Map<String, Object> map: lessons) {
            if (!ret.containsKey(LocalDate.parse(map.get("date").toString()))){
                LocalDate date = LocalDate.parse(map.get("date").toString());
                byte lessonNum = (Byte) map.get("lesson_number");
                int id = (Integer) map.get("id");
                String name = (String) map.get("name");
                String description = (String) map.get("description");
                ArrayList<LessonDTO> lst = new ArrayList<>();
                lst.add(new LessonDTO(lessonNum, id, name, description));
                ret.put(date,lst);
            }else{
                byte lessonNum = (Byte) map.get("lesson_number");
                int id = (Integer) map.get("id");
                String name = (String) map.get("name");
                String description = (String) map.get("description");
                ret.get(LocalDate.parse(map.get("date").toString())).add(new LessonDTO(lessonNum, id, name, description));
            }
        }
        return ret;
    }
}