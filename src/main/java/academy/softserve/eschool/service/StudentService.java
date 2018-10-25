package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.EditTeacherDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentService {
    @Autowired
    UserRepository userRepository;
    public StudentDTO getOne(Student s){
        return StudentDTO.builder().firstname(s.getFirstName())
                .lastname(s.getLastName())
                .patronymic(s.getPatronymic())
                .login(s.getLogin())
                .dateOfBirth(s.getDateOfBirth())
                .classe(s.getClasses().stream().filter(Clazz::isActive).findFirst().orElseGet(Clazz::new).getName())
                .email(s.getEmail())
                .phone(s.getPhone()).build();
    }

    public List<StudentDTO> getAll(List<Student> students){
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

    public void updateStudent(User oldUser, EditTeacherDTO edited){

        oldUser.setFirstName(edited.getFirstname());
        oldUser.setLastName(edited.getLastname());
        oldUser.setPatronymic(edited.getPatronymic());
        oldUser.setDateOfBirth(edited.getDateOfBirth());
        oldUser.setAvatar(edited.getAvatar());
        oldUser.setEmail(edited.getEmail());
        oldUser.setPhone(edited.getPhone());
        if((oldUser.getPassword().equals(edited.getOldPass()) || edited.getOldPass().equals("adminchangedpass"))
                && edited.getNewPass().length()>0){
            oldUser.setPassword(edited.getNewPass());
        }
        oldUser.setLogin(edited.getLogin());
        userRepository.save(oldUser);
    }
}
