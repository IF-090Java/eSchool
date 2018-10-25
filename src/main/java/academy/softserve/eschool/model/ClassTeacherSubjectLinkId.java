package academy.softserve.eschool.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClassTeacherSubjectLinkId implements Serializable{

    private int clazz_id;
    private int teacher_id;
    private int subject_id;
}
