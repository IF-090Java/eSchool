package academy.softserve.eschool.dto;

import academy.softserve.eschool.model.MarkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkTypeDTO {
    private String markType;
}
