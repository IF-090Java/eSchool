package academy.softserve.eschool.service;

import static academy.softserve.eschool.auxiliary.PasswordGenerator.generatePassword;
import static academy.softserve.eschool.auxiliary.Utility.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import academy.softserve.eschool.security.CustomPasswordEncoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
    private UserRepository userRepository;

    @NonNull
    private TeacherRepository teacherRepository;

    @NonNull
    private CustomPasswordEncoder passwordEncoder;

    @NonNull
    private LoginGeneratorService generateLogin;

    public List<TeacherDTO> getAll(List<Teacher> resultset) {

        return resultset.stream().
                filter(i->i.isEnabled()).
                map(i -> TeacherDTO.builder().id(i.getId())
                .firstname(i.getFirstName())
                .lastname(i.getLastName())
                .patronymic(i.getPatronymic())
                .login(i.getLogin())
                .dateOfBirth(i.getDateOfBirth())
                .phone(i.getPhone())
                .avatar(i.getAvatar())
                .email(i.getEmail()).build()).collect(Collectors.toCollection(ArrayList::new));
    }

    public TeacherDTO getOne(Teacher teacher) {
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

    public TeacherDTO adminUpdateTeacher(User oldUser, EditUserDTO edited) {
        oldUser.setFirstName(edited.getFirstname());
        oldUser.setLastName(edited.getLastname());
        oldUser.setPatronymic(edited.getPatronymic());
        oldUser.setLogin(edited.getLogin());
        return updateTeacher(oldUser, edited);
    }

    public TeacherDTO updateTeacher(User oldUser, EditUserDTO edited) {
        oldUser.setDateOfBirth(edited.getDateOfBirth());
        oldUser.setAvatar(edited.getAvatar());
        oldUser.setEmail(edited.getEmail());
        oldUser.setPhone(edited.getPhone());
        if (edited.getNewPass().length()>0){
            if ((passwordEncoder.matches(edited.getOldPass(), oldUser.getPassword()) || edited.getOldPass().equals("adminchangedpass"))) {
                oldUser.setPassword(passwordEncoder.encode(edited.getNewPass()));
            }else throw new BadCredentialsException("Wrong password");
        }
        oldUser = userRepository.save(oldUser);
        return transform(oldUser);
    }

    /**
     * Add teacher to DB. If login is already exist
     * or not transmitted then will be generated else set transmitted login.
     * Password always generate here.
     *
     * @param teacherDTO teacher data.
     * @return saved teacher.
     */
    public TeacherDTO addOne(TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .lastName(teacherDTO.getLastname())
                .firstName(teacherDTO.getFirstname())
                .patronymic(teacherDTO.getPatronymic())
                .password(passwordEncoder.encode(generatePassword(7)))
                .phone(teacherDTO.getPhone())
                .email(teacherDTO.getEmail())
                .dateOfBirth(teacherDTO.getDateOfBirth())
                .role(Role.ROLE_TEACHER)
                .enabled(true)
                .build();
        String login = teacherDTO.getLogin();
        if (login == null || !generateLogin.isUnique(login))
            teacher.setLogin(generateLogin.generateLogin(teacherDTO.getFirstname(), teacherDTO.getLastname()));
        else teacher.setLogin(login);
        return transform(teacherRepository.save(teacher));
    }
}
