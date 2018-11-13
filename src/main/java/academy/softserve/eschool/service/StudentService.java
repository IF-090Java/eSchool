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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static academy.softserve.eschool.auxiliary.PasswordGenerator.generatePassword;

@Service
@RequiredArgsConstructor
public class StudentService {
    @NonNull
    private UserRepository userRepository;

    @NonNull
    private ClassRepository classRepository;

    @NonNull
    private StudentRepository studentRepository;

    @NonNull
    private BCryptPasswordEncoder bcryptEncoder;

    @NonNull
    private LoginGeneratorService generateLogin;


    //todo bk ++ move all of your transfomers into some util class. Don't keep it within services
    public StudentDTO getOne(Student s){
        return StudentDTO.builder().firstname(s.getFirstName())
                .lastname(s.getLastName())
                .patronymic(s.getPatronymic())
                .login(s.getLogin())
                .dateOfBirth(s.getDateOfBirth())
                .classe(s.getClasses().stream().filter(Clazz::isActive).findFirst().orElseGet(Clazz::new).getName())
                .email(s.getEmail())
                .avatar(s.getAvatar())
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

    public void adminUpdateStudent(User oldUser, EditUserDTO edited){
        oldUser.setFirstName(edited.getFirstname());
        oldUser.setLastName(edited.getLastname());
        oldUser.setPatronymic(edited.getPatronymic());
        oldUser.setLogin(edited.getLogin());
        updateStudent(oldUser, edited);
    }

    public void updateStudent(User oldUser, EditUserDTO edited){
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
     * Add student to DB. If login is already exist
     * or not transmitted then will be generated else set transmitted login.
     * Password always generate here.
     * @param studentDTO student data.
     * @return saved student.
     */
    public Student addOne(StudentDTO studentDTO) {
        Student student = Student.builder()
                .lastName(studentDTO.getLastname())
                .firstName(studentDTO.getFirstname())
                .patronymic(studentDTO.getPatronymic())
                .password(bcryptEncoder.encode(generatePassword(7)))
                .phone(studentDTO.getPhone())
                .email(studentDTO.getEmail())
                .dateOfBirth(studentDTO.getDateOfBirth())
                .role(Role.ROLE_USER)
                .build();
        String login = studentDTO.getLogin();
        if (login.isEmpty() || !generateLogin.isUnique(login))
            student.setLogin(generateLogin.generateLogin(studentDTO.getFirstname(), studentDTO.getLastname()));
        else student.setLogin(login);
        Clazz clazz = classRepository.getOne(Integer.valueOf(studentDTO.getClassId()));
        student.getClasses().add(clazz);
        return studentRepository.save(student);
    }

    public void studentClassesRebinding(List<NYTransitionDTO> nyTransitionDTOS){
        List<Student> updatedStudentsList = new ArrayList<>();
         for (NYTransitionDTO nDTO : nyTransitionDTOS){
             if (nDTO.getNewClassId() != 0){
                 List<Student> studentList = studentRepository.findByClazzId(nDTO.getOldClassId());
                 for (Student student : studentList) {
                     List<Clazz> clazzes = student.getClasses();
                     clazzes.add(classRepository.findById(nDTO.getNewClassId()).orElse(null));
                     student.setClasses(clazzes);

                     //todo bk !!!!!!! Never do it again - calling repository method in loop. Just prepare all required data and save it once
                     updatedStudentsList.add(student);
                 }
             }
         }
         studentRepository.saveAll(updatedStudentsList);
    }
}
