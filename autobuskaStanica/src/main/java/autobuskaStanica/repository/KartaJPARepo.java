package autobuskaStanica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autobuskaStanica.model.Karta;

public interface KartaJPARepo extends JpaRepository<Karta, Integer>{
	@Query("SELECT k FROM Karta k WHERE k.korisnik2.korisnikID = :idKorisnika")
	List<Karta> rezervacijeKorisnika(@Param("idKorisnika") int idKorisnika);
}
