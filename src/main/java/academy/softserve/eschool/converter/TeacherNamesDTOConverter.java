package academy.softserve.eschool.converter;

import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherNamesDTOConverter {
    public static List<TeacherNamesDTO> convertList(List<Teacher> resultset){
        List<TeacherNamesDTO> result = new ArrayList<>();
        for (Teacher t : resultset){
            int id = t.getId();
            String firstname = t.getFirstName();
            String lastname = t.getLastName();
            String patronymic = t.getPatronymic();
            result.add(new TeacherNamesDTO(id,firstname,lastname,patronymic));
        }
        return result;
    }

    public static TeacherDTO convertOne(Teacher teacher){
        return new TeacherDTO(teacher.getId(),teacher.getFirstName(),teacher.getLastName(),teacher.getPatronymic(),teacher.getDateOfBirth(),
                teacher.getLogin(),teacher.getEmail(),teacher.getPhone());
    }
}
