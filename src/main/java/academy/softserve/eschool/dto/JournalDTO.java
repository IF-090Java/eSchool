package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JournalDTO {
    @ApiModelProperty(notes = "id of subject")
    private int idSubject;
    @ApiModelProperty(notes = "id of class")
    private int idClass;
    @ApiModelProperty(notes = "name of subject")
    private String subjectName;
    @ApiModelProperty(notes = "class full name")
    private String className;

    public JournalDTO(int idSubject,int idClass,String subjectName, String className) {
        this.idSubject = idSubject;
        this.idClass = idClass;
        this.subjectName = subjectName;
        this.className = className;
    }
}
