package autobuskaStanica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Stanica;

@Repository
public interface StanicaJPARepo extends JpaRepository<Stanica, Integer> {
	@Query("SELECT s FROM Stanica s WHERE s.destinacija.destinacijaID = :destinacijaID")
	List<Stanica> findByDestinacijaID(@Param("destinacijaID") int destinacijaID);
	
	@Query("SELECT s FROM Stanica s WHERE s.brStanice >= :polazna and s.brStanice < :dolazna and s.ruta.rutaID =:ruta")
	List<Stanica> getMedjustaniceNaRuti(@Param("polazna")int polazna, @Param("dolazna")int dolazna, @Param("ruta")int ruta);

	@Query("SELECT s FROM Stanica s WHERE s.ruta.rutaID =:rutaID and s.destinacija.destinacijaID = :destinacijaID")
	Stanica findByDestinacijaIDAndRutaID(@Param("rutaID") int rutaID, @Param("destinacijaID") int destinacijaID);
}
