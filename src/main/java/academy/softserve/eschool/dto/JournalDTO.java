package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JournalDTO {
    @ApiModelProperty(notes = "name of subject")
    private String subjectName;
    @ApiModelProperty(notes = "class full name")
    private String className;

    public JournalDTO(String subjectName, String className) {
        this.subjectName = subjectName;
        this.className = className;
    }
}
