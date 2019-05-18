package autobuskaStanica.beans;

import java.util.Date;

public class StanicaBean {

	private String odDestinacija;
	private String doDestinacija;
	private double cena;
	private Date polazak;
	private Date dolazak;
	private String prevoznik;
	private int polazakDest;
	private int dolazakDest;
	private int vrstaKarte;
	private int rutaID;

	public StanicaBean() {
		super();
	}

	public StanicaBean(String prevoznik, String odDestinacija, String doDestinacija, double cena, Date polazak, Date dolazak) {
		super();
		this.prevoznik = prevoznik;
		this.odDestinacija = odDestinacija;
		this.doDestinacija = doDestinacija;
		this.cena = cena;
		this.polazak = polazak;
		this.dolazak = dolazak;
	}

	public String getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(String prevoznik) {
		this.prevoznik = prevoznik;
	}

	public String getOdDestinacija() {
		return odDestinacija;
	}

	public void setOdDestinacija(String odDestinacija) {
		this.odDestinacija = odDestinacija;
	}

	public String getDoDestinacija() {
		return doDestinacija;
	}

	public void setDoDestinacija(String doDestinacija) {
		this.doDestinacija = doDestinacija;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Date getPolazak() {
		return polazak;
	}

	public void setPolazak(Date polazak) {
		this.polazak = polazak;
	}

	public Date getDolazak() {
		return dolazak;
	}

	public void setDolazak(Date dolazak) {
		this.dolazak = dolazak;
	}

	public int getPolazakDest() {
		return polazakDest;
	}

	public void setPolazakDest(int polazakDest) {
		this.polazakDest = polazakDest;
	}

	public int getDolazakDest() {
		return dolazakDest;
	}

	public void setDolazakDest(int dolazakDest) {
		this.dolazakDest = dolazakDest;
	}

	public int getVrstaKarte() {
		return vrstaKarte;
	}

	public void setVrstaKarte(int vrstaKarte) {
		this.vrstaKarte = vrstaKarte;
	}

	public int getRutaID() {
		return rutaID;
	}

	public void setRutaID(int rutaID) {
		this.rutaID = rutaID;
	}
	

}
