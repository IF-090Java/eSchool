package academy.softserve.eschool.converter;

import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.Teacher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDTOConverter {
    public static StudentDTO convertOne(Student s){
        int id = s.getId();
        String firstname = s.getFirstName();
        String lastname = s.getLastName();
        String patronymic = s.getPatronymic();
        String clazz = s.getClasses().stream().filter(clazz1 -> clazz1.isActive()).findFirst().get().getName();
        System.out.println("Clazz = " + clazz);
        Date dateofbirth = s.getDateOfBirth();
        String login = s.getLogin();
        String email = s.getEmail();
        String phone = s.getPhone();
        return new StudentDTO(id,firstname,lastname,patronymic,clazz,dateofbirth,login,email,phone);
    }

    public static List<StudentDTO> convertList(List<Student> students){
        List<StudentDTO> result = new ArrayList<>();
        for (Student s: students){
            int id = s.getId();
            String firstname = s.getFirstName();
            String lastname = s.getLastName();
            String patronymic = s.getPatronymic();
            String clazz = s.getClasses().stream().filter(clazz1 -> clazz1.isActive()).findFirst().get().getName();
            System.out.println("Clazz = " + clazz);
            Date dateofbirth = s.getDateOfBirth();
            String login = s.getLogin();
            String email = s.getEmail();
            String phone = s.getPhone();
            result.add(new StudentDTO(id,firstname,lastname,patronymic,clazz,dateofbirth,login,email,phone));
        }
        return result;
    }
}
