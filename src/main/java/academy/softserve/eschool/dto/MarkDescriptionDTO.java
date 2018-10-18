package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MarkDescriptionDTO {
    @ApiModelProperty(notes = "mark value")
    private Integer mark;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy/MM/dd")
    @ApiModelProperty(notes = "date of mark")
    private Date dateMark;
    @ApiModelProperty(notes = "type of mark")
    private String typeMark;

    public MarkDescriptionDTO(Integer mark, Date dateMark, String typeMark) {
        this.mark = mark;
        this.dateMark = dateMark;
        this.typeMark = typeMark;
    }
}
