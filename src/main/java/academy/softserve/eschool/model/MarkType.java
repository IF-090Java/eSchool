package academy.softserve.eschool.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This class represents the mark_type table of the DB
 */
@Entity
@Table(name = "mark_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_type")
    private String markType;

    private String description;

    @NotNull
    @Column(name = "is_active")
    boolean isActive;
}
