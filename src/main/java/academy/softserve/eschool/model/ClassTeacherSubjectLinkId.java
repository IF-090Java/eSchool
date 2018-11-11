package academy.softserve.eschool.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClassTeacherSubjectLinkId implements Serializable {

    @Min(1)
    private int clazz_id;
    
    @Min(1)
    private int teacher_id;
    
    @Min(1)
    private int subject_id;

}
