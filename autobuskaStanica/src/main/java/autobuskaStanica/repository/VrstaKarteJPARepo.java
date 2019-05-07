package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import autobuskaStanica.model.Vrstakarte;

public interface VrstaKarteJPARepo extends JpaRepository<Vrstakarte, Integer>{

	Vrstakarte findByVrstaKarteID(int id);
}
