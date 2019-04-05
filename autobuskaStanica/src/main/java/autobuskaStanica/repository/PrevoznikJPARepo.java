package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Prevoznik;

@Repository
public interface PrevoznikJPARepo extends JpaRepository<Prevoznik, Integer> {

}
