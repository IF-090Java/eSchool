package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class HomeworkDTO{
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd")
    @ApiModelProperty(notes = "date of homework")
    private Date date;
    @ApiModelProperty(notes = "homework")
    private String homework;
    @ApiModelProperty(notes = "file with homework or some resources")
    private byte[] file;

}
