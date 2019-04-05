package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ruta database table.
 * 
 */
@Entity
@NamedQuery(name="Ruta.findAll", query="SELECT r FROM Ruta r")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rutaID;

	private int brMesta;

	@Temporal(TemporalType.DATE)
	private Date datum;

	//bi-directional many-to-one association to Prevoznik
	@ManyToOne
	@JoinColumn(name="prevoznik")
	private Prevoznik prevoznikBean;

	//bi-directional many-to-one association to Tippolaska
	@ManyToOne
	@JoinColumn(name="tipPolaska")
	private Tippolaska tippolaska;

	//bi-directional many-to-one association to Stanica
	@OneToMany(mappedBy="ruta")
	private List<Stanica> stanicas;

	public Ruta() {
	}

	public int getRutaID() {
		return this.rutaID;
	}

	public void setRutaID(int rutaID) {
		this.rutaID = rutaID;
	}

	public int getBrMesta() {
		return this.brMesta;
	}

	public void setBrMesta(int brMesta) {
		this.brMesta = brMesta;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Prevoznik getPrevoznikBean() {
		return this.prevoznikBean;
	}

	public void setPrevoznikBean(Prevoznik prevoznikBean) {
		this.prevoznikBean = prevoznikBean;
	}

	public Tippolaska getTippolaska() {
		return this.tippolaska;
	}

	public void setTippolaska(Tippolaska tippolaska) {
		this.tippolaska = tippolaska;
	}

	public List<Stanica> getStanicas() {
		return this.stanicas;
	}

	public void setStanicas(List<Stanica> stanicas) {
		this.stanicas = stanicas;
	}

	public Stanica addStanica(Stanica stanica) {
		getStanicas().add(stanica);
		stanica.setRuta(this);

		return stanica;
	}

	public Stanica removeStanica(Stanica stanica) {
		getStanicas().remove(stanica);
		stanica.setRuta(null);

		return stanica;
	}

}