package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Destinacija;

@Repository
public interface DestinacijaJPARepo extends JpaRepository<Destinacija, Integer> {

}
