package autobuskaStanica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autobuskaStanica.model.Komentar;

public interface KomentarJPARepo extends JpaRepository<Komentar, Integer> {
		
	@Query("SELECT k FROM Komentar k WHERE k.prevoznik.prevoznikID = :prevoznikID") 
	 List<Komentar> vratiKomentareZaPrevoznika(@Param("prevoznikID") Integer prevoznikID);

}
