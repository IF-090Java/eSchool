package academy.softserve.eschool.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryEntryDTO {
    @ApiModelProperty(required = true, notes = "lesson date")
    private LocalDate date;
    @ApiModelProperty(required = true, notes = "lesson number")
    private byte lessonNumber;
    @ApiModelProperty(required = true, notes = "name of lesson")
    private String subjectName;
    @ApiModelProperty(notes = "home work")
    private String homeWork;
    @ApiModelProperty(notes = "mark received by student")
    private byte mark;
    @ApiModelProperty(notes = "teachers note")
    private String note;
}
