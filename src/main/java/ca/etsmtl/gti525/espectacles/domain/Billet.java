package ca.etsmtl.gti525.espectacles.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
@Table(name="Billets")
public class Billet extends Produit {

	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Representation representation;
	
	private Integer quantiteTotale;
	private Integer quantiteDisponible;
	private Integer quantiteReservee;
	
	public Billet(){
		
	}
	
	public Billet(Representation representation, Double prix, Integer quantiteDisponible){
		Assert.notNull(representation, "La representation ne doit pas etre null");
		this.representation = representation;
		this.quantiteDisponible = quantiteDisponible==null ? 
				                representation.getSalle().getNbPlaces() : quantiteDisponible;
	    this.quantiteTotale= quantiteDisponible==null ? 
						         representation.getSalle().getNbPlaces() : quantiteDisponible;
						         
 		super.setNom(representation.getNom());
		super.setPrix(prix);
	}


	public Representation getRepresentation() {
		return representation;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}

	public Integer getQuantiteDisponible() {
		return quantiteDisponible;
	}

	public void setQuantiteDisponible(Integer quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}
	
	
	/**
	 * 
	 * @param valeur
	 * @return la valeur decrementee
	 */
	public int decrementerQuantite(int valeur){
		int quantiteDecremente=0;
		if(valeur >= quantiteDisponible){
			quantiteDecremente = quantiteDisponible;
			quantiteDisponible=0;
		}
		else{
			quantiteDisponible-=valeur;
			quantiteDecremente = valeur;
		}
		return quantiteDecremente;
	}
	
	public int incrementerQuantite(int valeur){
		quantiteDisponible+=valeur;
		return valeur;
	}
	
}
