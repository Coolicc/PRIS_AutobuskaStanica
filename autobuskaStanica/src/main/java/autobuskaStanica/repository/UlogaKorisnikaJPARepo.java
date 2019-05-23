package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import autobuskaStanica.model.Ulogakorisnka;

public interface UlogaKorisnikaJPARepo extends JpaRepository<Ulogakorisnka, Integer> {
	Ulogakorisnka findByNazivUloge(String naziv);
}
