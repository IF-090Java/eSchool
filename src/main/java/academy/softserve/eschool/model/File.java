package academy.softserve.eschool.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="file")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="file_name")
    @NotNull
    private String fileName;
    
    @NotNull
    @Size(max=(int)(1000000*1.4))
    private String file;
    
    @NotNull
    @Column(name="file_type")
    private String fileType;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "file")
    private Lesson lesson;

    public File(@NotNull String fileName, @NotNull @Size(max = (int) (1000000 * 1.4)) String file, @NotNull String fileType, Lesson lesson) {
        this.fileName = fileName;
        this.file = file;
        this.fileType = fileType;
        this.lesson = lesson;
    }
}
