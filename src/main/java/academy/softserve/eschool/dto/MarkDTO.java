package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MarkDTO {
    @ApiModelProperty(notes = "mark value")
    private byte mark;
    @ApiModelProperty(notes = "note for mark")
    private String note;
}
