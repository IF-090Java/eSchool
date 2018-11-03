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
    @ApiModelProperty(notes = "student's id",required = true)
    private int idStudent;
    @ApiModelProperty(notes = "lesson's id",required = true)
    private int idLesson;
    @ApiModelProperty(notes = "mark value",required = true)
    private byte mark;
    @ApiModelProperty(notes = "note for mark",required = false)
    private String note;
}
