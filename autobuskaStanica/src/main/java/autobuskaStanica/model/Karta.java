package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the karta database table.
 * 
 */
@Entity
@NamedQuery(name="Karta.findAll", query="SELECT k FROM Karta k")
public class Karta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int kartaID;

	@Temporal(TemporalType.DATE)
	private Date datumProdaje;

	@Temporal(TemporalType.DATE)
	private Date datumRezervacije;

	private double konacnaCena;

	//bi-directional many-to-one association to Stanica
	@ManyToOne
	@JoinColumn(name="mestoDolaska")
	private Stanica stanica1;

	//bi-directional many-to-one association to Stanica
	@ManyToOne
	@JoinColumn(name="mestoPolaska")
	private Stanica stanica2;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="prodao")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="rezervisao")
	private Korisnik korisnik2;

	//bi-directional many-to-one association to Vrstakarte
	@ManyToOne
	@JoinColumn(name="vrstaKarte")
	private Vrstakarte vrstakarte;

	public Karta() {
	}

	public int getKartaID() {
		return this.kartaID;
	}

	public void setKartaID(int kartaID) {
		this.kartaID = kartaID;
	}

	public Date getDatumProdaje() {
		return this.datumProdaje;
	}

	public void setDatumProdaje(Date datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Date getDatumRezervacije() {
		return this.datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public double getKonacnaCena() {
		return this.konacnaCena;
	}

	public void setKonacnaCena(double konacnaCena) {
		this.konacnaCena = konacnaCena;
	}

	public Stanica getStanica1() {
		return this.stanica1;
	}

	public void setStanica1(Stanica stanica1) {
		this.stanica1 = stanica1;
	}

	public Stanica getStanica2() {
		return this.stanica2;
	}

	public void setStanica2(Stanica stanica2) {
		this.stanica2 = stanica2;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

	public Vrstakarte getVrstakarte() {
		return this.vrstakarte;
	}

	public void setVrstakarte(Vrstakarte vrstakarte) {
		this.vrstakarte = vrstakarte;
	}

}