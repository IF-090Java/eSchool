package academy.softserve.eschool.model;

import lombok.*;

import javax.persistence.*;

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

    private String mark_type;
}
