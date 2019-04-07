package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Korisnik;

@Repository
public interface KorisnikJPARepo extends JpaRepository<Korisnik, Integer> {
	
	Korisnik findByUsernameAndPassword(String username, String password);
}
