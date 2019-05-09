package autobuskaStanica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import autobuskaStanica.model.Komentar;

@Repository
@Transactional
public class KomentarRepo {
	
	@PersistenceContext
	EntityManager em;
	
	public Komentar saveKomentar(Komentar k){
		try{
			em.persist(k);
			return k;
		}
		catch(Exception e){
			return null;
		}
	}


	
}
