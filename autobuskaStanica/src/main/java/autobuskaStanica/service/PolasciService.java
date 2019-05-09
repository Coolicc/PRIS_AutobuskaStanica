package autobuskaStanica.service;

import java.util.List;

import autobuskaStanica.model.Ruta;

public interface PolasciService {
	public List<Ruta> ruteSaDestinacijama(int idDestPolazak, int idDestDolazak);
	public List<Double> ceneZaPolaske(List<Ruta> rute, int idDestPolazak, int idDestDolazak, int vrstaKarteId);
}
