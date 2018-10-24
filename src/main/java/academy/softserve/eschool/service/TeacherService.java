package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {
    public static List<TeacherDTO> getAll(List<Teacher> resultset){

        return resultset.stream().map(i->TeacherDTO.builder().id(i.getId())
            .firstname(i.getFirstName())
            .lastname(i.getLastName())
            .patronymic(i.getPatronymic())
            .login(i.getLogin())
            .dateOfBirth(i.getDateOfBirth())
            .phone(i.getPhone())
            .email(i.getEmail()).build()).collect(Collectors.toCollection(ArrayList::new));
    }

    public static TeacherDTO getOne(Teacher teacher){
        return TeacherDTO.builder().firstname(teacher.getFirstName())
                .lastname(teacher.getLastName())
                .patronymic(teacher.getPatronymic())
                .login(teacher.getLogin())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .dateOfBirth(teacher.getDateOfBirth())
                .build();
    }
}
