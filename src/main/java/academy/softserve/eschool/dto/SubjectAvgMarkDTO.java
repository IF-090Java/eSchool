package academy.softserve.eschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectAvgMarkDTO {
    
    /**
     * Average mark
     */
    private double avgMark;
    
    /**
     * Subject id
     */
    private int subjectId;
    
    /**
     * Subject name
     */
    private String subjectName;
}
