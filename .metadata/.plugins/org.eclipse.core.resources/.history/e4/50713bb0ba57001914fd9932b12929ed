package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the destinacija database table.
 * 
 */
@Entity
@NamedQuery(name="Destinacija.findAll", query="SELECT d FROM Destinacija d")
public class Destinacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int destinacijaID;

	private String drzava;

	private String grad;

	//bi-directional many-to-one association to Stanica
	@OneToMany(mappedBy="destinacija")
	private List<Stanica> stanicas;

	public Destinacija() {
	}

	public int getDestinacijaID() {
		return this.destinacijaID;
	}

	public void setDestinacijaID(int destinacijaID) {
		this.destinacijaID = destinacijaID;
	}

	public String getDrzava() {
		return this.drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return this.grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public List<Stanica> getStanicas() {
		return this.stanicas;
	}

	public void setStanicas(List<Stanica> stanicas) {
		this.stanicas = stanicas;
	}

	public Stanica addStanica(Stanica stanica) {
		getStanicas().add(stanica);
		stanica.setDestinacija(this);

		return stanica;
	}

	public Stanica removeStanica(Stanica stanica) {
		getStanicas().remove(stanica);
		stanica.setDestinacija(null);

		return stanica;
	}

}