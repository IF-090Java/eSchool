package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeworkFileDTO{
    @ApiModelProperty(notes = "id of lesson",required = true)
    private int idLesson;
    @ApiModelProperty(notes = "homework description")
    private String homework;
    @ApiModelProperty(notes = "base64 representation of file(use with fileName and fileType)")
    private String fileData;
    @ApiModelProperty(notes = "name of file(use with fileData and fileType)")
    private String fileName;
    @ApiModelProperty(notes = "mime type of file(use with fileData and fileName)")
    private String fileType;

}
