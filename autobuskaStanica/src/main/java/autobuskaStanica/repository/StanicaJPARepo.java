package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Stanica;

@Repository
public interface StanicaJPARepo extends JpaRepository<Stanica, Integer> {

}
