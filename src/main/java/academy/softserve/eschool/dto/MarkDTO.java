package academy.softserve.eschool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MarkDTO {
    private byte mark;
    private String note;
}
