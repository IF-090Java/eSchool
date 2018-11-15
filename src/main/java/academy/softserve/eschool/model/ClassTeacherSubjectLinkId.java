package academy.softserve.eschool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * This is an @IdClass for the class {@link ClassTeacherSubjectLink}
 *
 * @author Mariana Vorotniak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassTeacherSubjectLinkId implements Serializable {
    /**
     * Id of the class. It's minimal value must be 1.
     */
    @Min(1)
    private int clazz_id;
    /**
     * Id of the teacher.It's minimal value must be 1.
     */
    @Min(1)
    private int teacher_id;
    /**
     * Id of the subject.It's minimal value must be 1.
     */
    @Min(1)
    private int subject_id;

}
