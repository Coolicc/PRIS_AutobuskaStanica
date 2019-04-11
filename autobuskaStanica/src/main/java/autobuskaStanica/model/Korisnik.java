package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int korisnikID;
	
	@NotBlank(message="Morate uneti ime.")
	private String ime;
	
	@NotBlank(message="Morate uneti sifru.")
	@Size(min=8, max=32)
	private String password;
	
	@NotBlank(message="Morate uneti prezime.")
	private String prezime;
	
	@NotBlank(message="Morate uneti username.")
	//@UniqueElements(message="Vec postoji username")
	private String username;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="korisnik1")
	private List<Karta> kartas1;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="korisnik2")
	private List<Karta> kartas2;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Ulogakorisnka
	@ManyToOne
	@JoinColumn(name="ulogaID")
	private Ulogakorisnka ulogakorisnka;

	public Korisnik() {
	}

	public int getKorisnikID() {
		return this.korisnikID;
	}

	public void setKorisnikID(int korisnikID) {
		this.korisnikID = korisnikID;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Karta> getKartas1() {
		return this.kartas1;
	}

	public void setKartas1(List<Karta> kartas1) {
		this.kartas1 = kartas1;
	}

	public Karta addKartas1(Karta kartas1) {
		getKartas1().add(kartas1);
		kartas1.setKorisnik1(this);

		return kartas1;
	}

	public Karta removeKartas1(Karta kartas1) {
		getKartas1().remove(kartas1);
		kartas1.setKorisnik1(null);

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
		kartas2.setKorisnik2(this);

		return kartas2;
	}

	public Karta removeKartas2(Karta kartas2) {
		getKartas2().remove(kartas2);
		kartas2.setKorisnik2(null);

		return kartas2;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Ulogakorisnka getUlogakorisnka() {
		return this.ulogakorisnka;
	}

	public void setUlogakorisnka(Ulogakorisnka ulogakorisnka) {
		this.ulogakorisnka = ulogakorisnka;
	}

}