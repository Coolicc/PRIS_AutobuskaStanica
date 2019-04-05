package autobuskaStanica.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tippolaska database table.
 * 
 */
@Entity
@NamedQuery(name="Tippolaska.findAll", query="SELECT t FROM Tippolaska t")
public class Tippolaska implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tipPolaskaID;

	private String nazivTipa;

	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="tippolaska")
	private List<Ruta> rutas;

	public Tippolaska() {
	}

	public int getTipPolaskaID() {
		return this.tipPolaskaID;
	}

	public void setTipPolaskaID(int tipPolaskaID) {
		this.tipPolaskaID = tipPolaskaID;
	}

	public String getNazivTipa() {
		return this.nazivTipa;
	}

	public void setNazivTipa(String nazivTipa) {
		this.nazivTipa = nazivTipa;
	}

	public List<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Ruta addRuta(Ruta ruta) {
		getRutas().add(ruta);
		ruta.setTippolaska(this);

		return ruta;
	}

	public Ruta removeRuta(Ruta ruta) {
		getRutas().remove(ruta);
		ruta.setTippolaska(null);

		return ruta;
	}

}