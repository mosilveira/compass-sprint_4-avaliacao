package uol.compass.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.enums.Position;

import java.util.List;

public interface AssociateRepository extends JpaRepository<Associate, Long> {

    List<Associate> findAllByPosition(Position position);

    List<Associate> findAllByPartyName(String name);
}
