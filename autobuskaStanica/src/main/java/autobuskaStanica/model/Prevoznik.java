package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prevoznik database table.
 * 
 */
@Entity
@NamedQuery(name="Prevoznik.findAll", query="SELECT p FROM Prevoznik p")
public class Prevoznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int prevoznikID;

	private String adresa;

	private String email;

	private String grad;

	private String naziv;

	private String telefon;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="prevoznik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="prevoznikBean")
	private List<Ruta> rutas;

	public Prevoznik() {
	}

	public int getPrevoznikID() {
		return this.prevoznikID;
	}

	public void setPrevoznikID(int prevoznikID) {
		this.prevoznikID = prevoznikID;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrad() {
		return this.grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPrevoznik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPrevoznik(null);

		return komentar;
	}

	public List<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Ruta addRuta(Ruta ruta) {
		getRutas().add(ruta);
		ruta.setPrevoznikBean(this);

		return ruta;
	}

	public Ruta removeRuta(Ruta ruta) {
		getRutas().remove(ruta);
		ruta.setPrevoznikBean(null);

		return ruta;
	}

}