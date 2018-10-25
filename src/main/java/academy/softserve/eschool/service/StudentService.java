package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Role;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static academy.softserve.eschool.auxiliary.LoginGeneratorController.transliteration;
import static academy.softserve.eschool.auxiliary.PasswordGenerator.generatePassword;

public class StudentService {

    @Autowired
    private static ClassRepository classRepository;

    @Autowired
    private static StudentRepository studentRepository;
    public static StudentDTO getOne(Student s){
        return StudentDTO.builder().firstname(s.getFirstName())
                .lastname(s.getLastName())
                .patronymic(s.getPatronymic())
                .login(s.getLogin())
                .dateOfBirth(s.getDateOfBirth())
                .classe(s.getClasses().stream().filter(Clazz::isActive).findFirst().orElseGet(Clazz::new).getName())
                .email(s.getEmail())
                .phone(s.getPhone()).build();
    }

    public static List<StudentDTO> getAll(List<Student> students){
        return students.stream().map(i->StudentDTO.builder().Id(i.getId())
                .firstname(i.getFirstName())
                .lastname(i.getLastName())
                .patronymic(i.getPatronymic())
                .dateOfBirth(i.getDateOfBirth())
                .classe(i.getClasses().stream().filter(Clazz::isActive).findAny().orElseGet(Clazz::new).getName())
                .email(i.getEmail())
                .phone(i.getPhone()).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void addStudentServ(final StudentDTO studentDTO){
        Student student = Student.builder()
                .firstName(studentDTO.getFirstname())
                .lastName(studentDTO.getLastname())
                .patronymic(studentDTO.getPatronymic())
                .login(transliteration(studentDTO.getLastname()))
                .password(generatePassword(7))
                .avatar(null)
                .dateOfBirth(studentDTO.getDateOfBirth())
                .email(studentDTO.getEmail())
                .phone(studentDTO.getPhone())
                .role(Role.ROLE_USER)
                .build();
        student.getClasses().add(new Clazz());
        studentRepository.save(student);
    }
}
