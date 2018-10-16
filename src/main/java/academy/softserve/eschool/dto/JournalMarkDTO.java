package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class JournalMarkDTO {
    @ApiModelProperty(notes = "name of student")
    private String studentFullName;
    @ApiModelProperty(notes = "array of marks")
    private List<MarkDescriptionDTO> marks;

    public JournalMarkDTO(String studentFullName, List<MarkDescriptionDTO> marks) {
        this.studentFullName = studentFullName;
        this.marks = marks;
    }
}
