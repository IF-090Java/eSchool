package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.EditUserDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {
    @NonNull
    UserRepository userRepository;

    public EditUserDTO deactivate(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }else {
            throw new UsernameNotFoundException("No user with given id");
        }
        user.setEnabled(false);
        userRepository.save(user);
        return new EditUserDTO().builder().
                login(user.getLogin()).
                firstname(user.getFirstName()).
                lastname(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public TeacherDTO getUserByEmail(String mail){
        User user = userRepository.findByEmail(mail);
        if(user == null)
            throw new UsernameNotFoundException("No user with such mail");
        return TeacherDTO.builder().firstname(user.getFirstName())
                .lastname(user.getLastName())
                .patronymic(user.getPatronymic())
                .login(user.getLogin())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .avatar(user.getAvatar())
                .build();
    }

    public TeacherDTO getUserByLogin(String username){
        User user = userRepository.findByLogin(username);
        if(user == null)
            throw new UsernameNotFoundException("No user with such login");
        return TeacherDTO.builder().firstname(user.getFirstName())
                .lastname(user.getLastName())
                .patronymic(user.getPatronymic())
                .login(user.getLogin())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .avatar(user.getAvatar())
                .build();
    }
}
