package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {
    @ApiModelProperty(notes = "mark of id(used only when controller return object to browser in post method")
    private int idMark;
    @ApiModelProperty(notes = "student's id",required = true)
    private int idStudent;
    @ApiModelProperty(notes = "lesson's id",required = true)
    private int idLesson;
    @ApiModelProperty(notes = "mark value")
    private Byte mark;
    @ApiModelProperty(notes = "note for mark")
    private String note;
}
