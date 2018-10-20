package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class JournalMarkDTO {
    @ApiModelProperty(notes = "name of student")
    private String studentFullName;
    @ApiModelProperty(notes = "array of marks")
    private List<MarkDescriptionDTO> marks;

}
