package autobuskaStanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuskaStanica.model.Ruta;

@Repository
public interface RutaJPARepo extends JpaRepository<Ruta, Integer> {
	Ruta findByRutaID(int id);
}
