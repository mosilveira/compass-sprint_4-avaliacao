package uol.compass.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.avaliacao.entity.Party;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
