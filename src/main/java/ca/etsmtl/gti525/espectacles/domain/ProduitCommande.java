package ca.etsmtl.gti525.espectacles.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class ProduitCommande {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	private Set<Panier> paniers = new HashSet<Panier>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Produit produit;
	private int quantite;
	
	public ProduitCommande(){
		
	}
	
	public ProduitCommande(Produit produit){
		this(produit, 1);
	}
	
	public ProduitCommande(Produit produit, int quantite){
		
		Assert.notNull(produit, "The product must not be null");
		Assert.isTrue(quantite > 0, "La quantite de produit doit etre > 0");
		
		this.quantite = quantite;
		this.produit = produit;
	}
	
	public double getPrixUnitaire(){
		return produit.getPrix();
	}
	
	public double getPrixTotal(){
		return getPrixUnitaire()*quantite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public void incrementQuantityBy(int value){
		this.quantite = this.quantite+value;
	}
	
}
