package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Tippolaska;

@Repository
public interface TipRuteJPARepo extends JpaRepository<Tippolaska, Integer> {

}
