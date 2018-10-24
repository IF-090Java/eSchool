package academy.softserve.eschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherNamesDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String patronymic;
    public Integer getId() {
        return id;
    }
}
