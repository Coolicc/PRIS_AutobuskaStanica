package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the stanica database table.
 * 
 */
@Entity
@NamedQuery(name="Stanica.findAll", query="SELECT s FROM Stanica s")
public class Stanica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stanicaID;

	private int brSlobodnihMesta;

	private int brStanice;

	private int cena;

	@Temporal(TemporalType.DATE)
	private Date dolazak;

	@Temporal(TemporalType.DATE)
	private Date polazak;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="stanica1")
	private List<Karta> kartas1;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="stanica2")
	private List<Karta> kartas2;

	//bi-directional many-to-one association to Destinacija
	@ManyToOne
	@JoinColumn(name="destinacijaID")
	private Destinacija destinacija;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	@JoinColumn(name="rutaID")
	private Ruta ruta;

	public Stanica() {
	}

	public int getStanicaID() {
		return this.stanicaID;
	}

	public void setStanicaID(int stanicaID) {
		this.stanicaID = stanicaID;
	}

	public int getBrSlobodnihMesta() {
		return this.brSlobodnihMesta;
	}

	public void setBrSlobodnihMesta(int brSlobodnihMesta) {
		this.brSlobodnihMesta = brSlobodnihMesta;
	}

	public int getBrStanice() {
		return this.brStanice;
	}

	public void setBrStanice(int brStanice) {
		this.brStanice = brStanice;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Date getDolazak() {
		return this.dolazak;
	}

	public void setDolazak(Date dolazak) {
		this.dolazak = dolazak;
	}

	public Date getPolazak() {
		return this.polazak;
	}

	public void setPolazak(Date polazak) {
		this.polazak = polazak;
	}

	public List<Karta> getKartas1() {
		return this.kartas1;
	}

	public void setKartas1(List<Karta> kartas1) {
		this.kartas1 = kartas1;
	}

	public Karta addKartas1(Karta kartas1) {
		getKartas1().add(kartas1);
		kartas1.setStanica1(this);

		return kartas1;
	}

	public Karta removeKartas1(Karta kartas1) {
		getKartas1().remove(kartas1);
		kartas1.setStanica1(null);

		return kartas1;
	}

	public List<Karta> getKartas2() {
		return this.kartas2;
	}

	public void setKartas2(List<Karta> kartas2) {
		this.kartas2 = kartas2;
	}

	public Karta addKartas2(Karta kartas2) {
		getKartas2().add(kartas2);
		kartas2.setStanica2(this);

		return kartas2;
	}

	public Karta removeKartas2(Karta kartas2) {
		getKartas2().remove(kartas2);
		kartas2.setStanica2(null);

		return kartas2;
	}

	public Destinacija getDestinacija() {
		return this.destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}