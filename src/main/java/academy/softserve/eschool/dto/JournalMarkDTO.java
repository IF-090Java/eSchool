package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class JournalMarkDTO {
    @ApiModelProperty(notes = "id of student")
    private int idStudent;
    @ApiModelProperty(notes = "name of student")
    private String studentFullName;
    @ApiModelProperty(notes = "array of marks")
    private List<MarkDescriptionDTO> marks;

}
