package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vrstakarte database table.
 * 
 */
@Entity
@NamedQuery(name="Vrstakarte.findAll", query="SELECT v FROM Vrstakarte v")
public class Vrstakarte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int vrstaKarteID;

	private String nazivVrste;

	private double popust;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="vrstakarte")
	private List<Karta> kartas;

	public Vrstakarte() {
	}

	public int getVrstaKarteID() {
		return this.vrstaKarteID;
	}

	public void setVrstaKarteID(int vrstaKarteID) {
		this.vrstaKarteID = vrstaKarteID;
	}

	public String getNazivVrste() {
		return this.nazivVrste;
	}

	public void setNazivVrste(String nazivVrste) {
		this.nazivVrste = nazivVrste;
	}

	public double getPopust() {
		return this.popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setVrstakarte(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setVrstakarte(null);

		return karta;
	}

}