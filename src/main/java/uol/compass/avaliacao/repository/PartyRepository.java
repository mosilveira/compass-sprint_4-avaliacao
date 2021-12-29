package uol.compass.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Ideology;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findAllByIdeology(Ideology ideology);
}
