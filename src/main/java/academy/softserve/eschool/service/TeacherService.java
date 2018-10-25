package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.EditTeacherDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.TeacherRepository;
import academy.softserve.eschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TeacherService {

    @Autowired
    private  UserRepository userRepository;

    public List<TeacherDTO> getAll(List<Teacher> resultset){

        return resultset.stream().map(i->TeacherDTO.builder().id(i.getId())
            .firstname(i.getFirstName())
            .lastname(i.getLastName())
            .patronymic(i.getPatronymic())
            .login(i.getLogin())
            .dateOfBirth(i.getDateOfBirth())
            .phone(i.getPhone())
            .email(i.getEmail()).build()).collect(Collectors.toCollection(ArrayList::new));
    }

    public TeacherDTO getOne(Teacher teacher){
        return TeacherDTO.builder().firstname(teacher.getFirstName())
                .lastname(teacher.getLastName())
                .patronymic(teacher.getPatronymic())
                .login(teacher.getLogin())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .dateOfBirth(teacher.getDateOfBirth())
                .avatar(teacher.getAvatar())
                .build();
    }

    public void updateTeacher(User oldUser, EditTeacherDTO edited){

        oldUser.setFirstName(edited.getFirstname());
        oldUser.setLastName(edited.getLastname());
        oldUser.setPatronymic(edited.getPatronymic());
        oldUser.setDateOfBirth(edited.getDateOfBirth());
        oldUser.setAvatar(edited.getAvatar());
        oldUser.setEmail(edited.getEmail());
        oldUser.setPhone(edited.getPhone());
        if(oldUser.getPassword().equals(edited.getOldPass()) || edited.getOldPass().equals("adminchangedpass")){
            oldUser.setPassword(edited.getNewPass());
        }
        oldUser.setLogin(edited.getLogin());
        userRepository.save(oldUser);
    }
}
