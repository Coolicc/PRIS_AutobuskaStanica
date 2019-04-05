package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ulogakorisnka database table.
 * 
 */
@Entity
@NamedQuery(name="Ulogakorisnka.findAll", query="SELECT u FROM Ulogakorisnka u")
public class Ulogakorisnka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ulogaID;

	private String nazivUloge;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="ulogakorisnka")
	private List<Korisnik> korisniks;

	public Ulogakorisnka() {
	}

	public int getUlogaID() {
		return this.ulogaID;
	}

	public void setUlogaID(int ulogaID) {
		this.ulogaID = ulogaID;
	}

	public String getNazivUloge() {
		return this.nazivUloge;
	}

	public void setNazivUloge(String nazivUloge) {
		this.nazivUloge = nazivUloge;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setUlogakorisnka(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setUlogakorisnka(null);

		return korisnik;
	}

}