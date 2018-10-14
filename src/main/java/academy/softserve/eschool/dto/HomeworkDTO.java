package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class HomeworkDTO{
    @ApiModelProperty(notes = "date of homework")
    private Date date;
    @ApiModelProperty(notes = "homework")
    private String homework;
    @ApiModelProperty(notes = "file with homework or some resources")
    private String file;

    public HomeworkDTO(Date date, String homework, String file) {
        this.date = date;
        this.homework = homework;
        this.file = file;
    }
}
