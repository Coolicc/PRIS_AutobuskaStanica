package autobuskaStanica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autobuskaStanica.model.Ruta;
import autobuskaStanica.model.Stanica;
import autobuskaStanica.model.Vrstakarte;
import autobuskaStanica.repository.StanicaJPARepo;
import autobuskaStanica.repository.VrstaKarteJPARepo;

@Service
public class PolasciServiceImpl implements PolasciService{

	@Autowired
	StanicaJPARepo stanicaJpaRepo;
	
	@Autowired
	VrstaKarteJPARepo vrstaKarteJpaRepo;
	
	@Override
	public List<Ruta> ruteSaDestinacijama(int idDestPolazak, int idDestDolazak) {
		List<Stanica> polazakStanice = stanicaJpaRepo.findByDestinacijaID(idDestPolazak);
		List<Ruta> rezRuta = new ArrayList<>();
		for(Stanica p: polazakStanice) {
			Ruta ruta = p.getRuta();
			List<Stanica> staniceRute = ruta.getStanicas();
			for(Stanica s:staniceRute) {
				if(s.getDestinacija().getDestinacijaID() == idDestDolazak && s.getBrStanice()>p.getBrStanice()) {
					rezRuta.add(ruta);
					break;
				}
			}
		}
		return rezRuta;
	}

	@Override
	public List<Double> ceneZaPolaske(List<Ruta> rute, int idDestPolazak, int idDestDolazak, int vrstaKarteId) {
		List<Double> cene = new ArrayList<>();
		for(Ruta r: rute) {
			List<Stanica> stanice = r.getStanicas();
			int polazna = 0; 
			int dolazna = 0;
			double cena = 0;
			for(Stanica s:stanice) {
				if(s.getDestinacija().getDestinacijaID()==idDestPolazak) {
					polazna = s.getBrStanice();
				}
				if(s.getDestinacija().getDestinacijaID() == idDestDolazak) {
					dolazna = s.getBrStanice();
				}
			}
			List<Stanica> medjuStanice= stanicaJpaRepo.getMedjustaniceNaRuti(polazna, dolazna, r.getRutaID());
			for(Stanica s:medjuStanice) {
				cena+=s.getCena();
			}
			Vrstakarte vrstaK = vrstaKarteJpaRepo.findByVrstaKarteID(vrstaKarteId);
			cena= cena * vrstaK.getPopust();
			cene.add(cena);
		}
		return cene;
	}
	
}
