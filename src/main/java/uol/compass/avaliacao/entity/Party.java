package uol.compass.avaliacao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String initials;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ideology ideology;

    @Column(nullable = false)
    private LocalDate foundationDate;
}
