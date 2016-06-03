package ca.etsmtl.gti525.espectacles.domain;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractProduit {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id; 

	@Column(nullable = false)
	@NotNull
	private String nom; 
	private String description; 

	@Column(nullable = false) 
	@NotNull
	private Double prix; 

	private Double rating;
	
	public AbstractProduit(){
		
	}
	
	public AbstractProduit(String nom, String description, double prix){
		this.rating = 0 + (Math.random() * (5 - 0));
	}
	
	public Long getId(){
		return this.id;
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrix() {
		
		return prix;
	}
	
	public String getPrixArrondi() {
		DecimalFormat df = new DecimalFormat("#####.00");
		
		return df.format(prix);
	}
	
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	public Double getRating(){
		return this.rating;
	}
	
	public void setRating(Double rating){
		this.rating = rating;
	}
	

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractProduit produit = (AbstractProduit) obj;

		return this.id.equals(produit.getId());
	}
}
