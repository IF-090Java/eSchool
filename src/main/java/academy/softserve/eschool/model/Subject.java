package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import academy.softserve.eschool.constraint.annotation.RegexPattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = "CTSlinks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="subject")
@ToString(of = {"id","name"})
public class Subject {
    public final static String SUBJECT_NAME_PATTERN = "[А-ЯІЇЄҐа-яіїєґ()' -]+";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    @Size(max=50)
    @RegexPattern(pattern = SUBJECT_NAME_PATTERN, message = "Input must match " + SUBJECT_NAME_PATTERN)
    private String name;
    
    @Size(max=500)
    private String description;
    
    /**
     * Set of {@link ClassTeacherSubjectLink} objects. One subject may contains many Class-Teacher-Subject pairs.
     */
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "subject")
    private final Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
}
