package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.ScheduleDTO;
import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ClassRepository classRepository;

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

    public List<SubjectDTO> convertFromObject(List<Object[]> somelist)
    {
        List<SubjectDTO> list = new ArrayList<>();
        for (int i = 0; i < somelist.size(); i ++)
            list.add(new SubjectDTO((int)somelist.get(i)[0], (String) somelist.get(i)[1], (String) somelist.get(i)[2]));
        return list;
    }

}
