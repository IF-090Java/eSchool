package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.dto.SubjectDTO;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        List<Object[]> monday = lessonRepository.scheduleByClassId(0, id_class);
        List<Object[]> tuesday = lessonRepository.scheduleByClassId(1, id_class);
        List<Object[]> wednesday = lessonRepository.scheduleByClassId(2, id_class);
        List<Object[]> thursday = lessonRepository.scheduleByClassId(3, id_class);
        List<Object[]> friday = lessonRepository.scheduleByClassId(4, id_class);

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

        Date start = scheduleDTO.getStartOfSemester();
        Date end = scheduleDTO.getEndOfSemester();
        List<SubjectDTO> monday = scheduleDTO.getMondaySubjects();
        List<SubjectDTO> tuesday = scheduleDTO.getTuesdaySubjects();
        List<SubjectDTO> wednesday = scheduleDTO.getWednesdaySubjects();
        List<SubjectDTO> thursday = scheduleDTO.getThursdaySubjects();
        List<SubjectDTO> friday = scheduleDTO.getFridaySubjects();

        int id_class = scheduleDTO.getClassName().getId();
        Clazz clazz = classRepository.findById(id_class).get();

        LocalDate startl = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endl = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        saveFunction(monday, startl, endl, DayOfWeek.MONDAY, clazz);
        saveFunction(tuesday, startl, endl, DayOfWeek.TUESDAY, clazz);
        saveFunction(wednesday, startl, endl, DayOfWeek.THURSDAY, clazz);
        saveFunction(thursday, startl, endl, DayOfWeek.THURSDAY, clazz);
        saveFunction(friday, startl, endl, DayOfWeek.FRIDAY, clazz);
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
                                    .lessonNumber((byte)(i+1))
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

    //this is a method to convert Object[] into SubjectDTO
    public List<SubjectDTO> convertFromObject(List<Object[]> somelist)
    {
        List<SubjectDTO> list = new ArrayList<>();
        for (int i = 0; i < somelist.size(); i ++)
            list.add(new SubjectDTO((int)somelist.get(i)[0], (String) somelist.get(i)[1], (String) somelist.get(i)[2]));
        return list;
    }

}
