package academy.softserve.eschool.service;

import static academy.softserve.eschool.auxiliary.PasswordGenerator.generatePassword;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.model.User.Role;
import academy.softserve.eschool.repository.TeacherRepository;
import academy.softserve.eschool.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TeacherService {

    @NonNull
    private  UserRepository userRepository;

    @NonNull
    private TeacherRepository teacherRepository;

    @NonNull
    private BCryptPasswordEncoder bcryptEncoder;

    @NonNull
    private LoginGeneratorService generateLogin;

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

    public void updateTeacher(User oldUser, EditUserDTO edited, String role){

        if (role.equals("ADMIN")) {
            oldUser.setFirstName(edited.getFirstname());
            oldUser.setLastName(edited.getLastname());
            oldUser.setPatronymic(edited.getPatronymic());
            oldUser.setLogin(edited.getLogin());
        }
        oldUser.setDateOfBirth(edited.getDateOfBirth());
        oldUser.setAvatar(edited.getAvatar());
        oldUser.setEmail(edited.getEmail());
        oldUser.setPhone(edited.getPhone());
        if((oldUser.getPassword().equals(edited.getOldPass()) || edited.getOldPass().equals("adminchangedpass"))
                 && edited.getNewPass().length()>0){
            oldUser.setPassword(bcryptEncoder.encode(edited.getNewPass()));
        }
        userRepository.save(oldUser);
    }

    /**
     * Add teacher to DB. If login is already exist
     * or not transmitted then will be generated else set transmitted login.
     * Password always generate here.
     * @param teacherDTO teacher data.
     * @return saved teacher.
     */
    public Teacher addOne(TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .lastName(teacherDTO.getLastname())
                .firstName(teacherDTO.getFirstname())
                .patronymic(teacherDTO.getPatronymic())
                .password(bcryptEncoder.encode(generatePassword(7)))
                .phone(teacherDTO.getPhone())
                .email(teacherDTO.getEmail())
                .dateOfBirth(teacherDTO.getDateOfBirth())
                .role(Role.ROLE_TEACHER)
                .build();
        String login = teacherDTO.getLogin();
        if (login == null || !generateLogin.isUnique(login))
            teacher.setLogin(generateLogin.generateLogin(teacherDTO.getFirstname(), teacherDTO.getLastname()));
        else teacher.setLogin(login);
        return teacherRepository.save(teacher);
    }
}
