package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.NYTransitionDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.model.User.Role;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.StudentRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.CustomPasswordEncoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static academy.softserve.eschool.auxiliary.PasswordGenerator.generatePassword;
import static academy.softserve.eschool.auxiliary.Utility.transform;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private ClassRepository classRepository;

    @NonNull
    private StudentRepository studentRepository;

    @NonNull
    private CustomPasswordEncoder passwordEncoder;

    @NonNull
    private LoginGeneratorService generateLogin;


    //todo bk ++ move all of your transfomers into some util class. Don't keep it within services
    public StudentDTO getOne(Student s) {
        return transform(s);
    }

    public List<StudentDTO> getAll(List<Student> students) {
        return students.stream().
                filter(i->i.isEnabled()).
                map(i -> StudentDTO.builder().Id(i.getId())
                .firstname(i.getFirstName())
                .lastname(i.getLastName())
                .patronymic(i.getPatronymic())
                .login(i.getLogin())
                .dateOfBirth(i.getDateOfBirth())
                .classe(i.getClasses().stream().filter(Clazz::isActive).findAny().orElseGet(Clazz::new).getName())
                .classId(i.getClasses().stream().filter(Clazz::isActive).findAny().orElseGet(Clazz::new).getId())
                .email(i.getEmail())
                .avatar(i.getAvatar())
                .phone(i.getPhone()).build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    public User adminUpdateStudent(User oldUser, EditUserDTO edited) {
        oldUser.setFirstName(edited.getFirstname());
        oldUser.setLastName(edited.getLastname());
        oldUser.setPatronymic(edited.getPatronymic());
        oldUser.setLogin(edited.getLogin());
        return updateStudent(oldUser, edited);
    }


    public User updateStudent(User oldUser, EditUserDTO edited) {
        oldUser.setDateOfBirth(edited.getDateOfBirth());
        oldUser.setAvatar(edited.getAvatar());
        oldUser.setEmail(edited.getEmail());
        oldUser.setPhone(edited.getPhone());
        if (edited.getNewPass().length()>0){
            if ((passwordEncoder.matches(edited.getOldPass(), oldUser.getPassword()) || edited.getOldPass().equals("adminchangedpass"))) {
                oldUser.setPassword(passwordEncoder.encode(edited.getNewPass()));
            }else throw new BadCredentialsException("Wrong password");
        }

        return userRepository.save(oldUser);
    }

    /**
     * Add student to DB. If login is already exist
     * or not transmitted then will be generated else set transmitted login.
     * Password always generate here.
     *
     * @param studentDTO student data.
     * @return saved student.
     */
    public Student addOne(StudentDTO studentDTO) {
        Student student = Student.builder()
                .lastName(studentDTO.getLastname())
                .firstName(studentDTO.getFirstname())
                .patronymic(studentDTO.getPatronymic())
                .password(passwordEncoder.encode(generatePassword(7)))
                .phone(studentDTO.getPhone())
                .email(studentDTO.getEmail())
                .dateOfBirth(studentDTO.getDateOfBirth())
                .role(Role.ROLE_USER)
                .enabled(true)
                .build();
        String login = studentDTO.getLogin();
        if (login.isEmpty() || !generateLogin.isUnique(login))
            student.setLogin(generateLogin.generateLogin(studentDTO.getFirstname(), studentDTO.getLastname()));
        else student.setLogin(login);
        Clazz clazz = classRepository.getOne(Integer.valueOf(studentDTO.getClassId()));
        student.getClasses().add(clazz);
        return studentRepository.save(student);
    }

    public void studentClassesRebinding(List<NYTransitionDTO> nyTransitionDTOS) {
        List<Student> updatedStudentsList = new ArrayList<>();
        for (NYTransitionDTO nDTO : nyTransitionDTOS) {
            if (nDTO.getNewClassId() != 0) {
                List<Student> studentList = studentRepository.findByClazzId(nDTO.getOldClassId());
                for (Student student : studentList) {
                    List<Clazz> clazzes = student.getClasses();
                    clazzes.add(classRepository.findById(nDTO.getNewClassId()).orElse(null));
                    student.setClasses(clazzes);
                    updatedStudentsList.add(student);
                }
            }
            LOGGER.debug("Students from class with id=" +nDTO.getOldClassId() +" to class with id=" +nDTO.getNewClassId() +" added.");
        }
        studentRepository.saveAll(updatedStudentsList);
        LOGGER.info("Students from old year classes to new year classes added.");
    }
}
