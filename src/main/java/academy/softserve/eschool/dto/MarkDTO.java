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
    @ApiModelProperty(notes = "student's id")
    private int idStudent;
    @ApiModelProperty(notes = "lesson's id")
    private int idLesson;
    @ApiModelProperty(notes = "mark value")
    private byte mark;
    @ApiModelProperty(notes = "note for mark")
    private String note;
}
