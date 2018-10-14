package academy.softserve.eschool.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherNamesDTO {
    private Integer id;
    private String firstname;
    private String lastname;

}
