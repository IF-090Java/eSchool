package academy.softserve.eschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Homework {
    private Date date;
    private String homework;
    private String file;
}
