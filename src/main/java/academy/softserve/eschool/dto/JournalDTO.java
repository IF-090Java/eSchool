package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JournalDTO {
    @ApiModelProperty(notes = "id of subject")
    private int idSubject;
    @ApiModelProperty(notes = "id of class")
    private int idClass;
    @ApiModelProperty(notes = "name of subject")
    private String subjectName;
    @ApiModelProperty(notes = "class full name")
    private String className;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd")
    @ApiModelProperty(notes = "start date of journal")
    private Date startDate;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd")
    @ApiModelProperty(notes = "end date of journal")
    private Date endDate;

}
