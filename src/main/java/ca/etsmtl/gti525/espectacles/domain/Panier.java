package ca.etsmtl.gti525.espectacles.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Yanis
 *
 */
@Entity
@Component
@Scope("session")
public class Panier {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
	private Client client;
	private String sessionId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ProduitCommande> products = new HashSet<ProduitCommande>();
	
	public Panier(){
		
	}
	
	public void addProduct(Produit product, int quantity){
		boolean isProductAdded = false;
		//Check if product is already here
		for(ProduitCommande prod : products){
			if(prod.getProduit().equals(product)){
				prod.incrementQuantityBy(quantity);
				isProductAdded = true;
			}
		}
		//else, create a new one
		if(isProductAdded == false){
			products.add(new ProduitCommande(product, quantity));
		}
	}
	
	public void removeProduct(Produit product){
		ProduitCommande productToRemove = null;
		for(ProduitCommande prod : products){
			if(prod.getProduit().equals(product)){
				productToRemove = prod;
			}
		}
		products.remove(productToRemove);
	}
	
	public double getTotal(){
		double total = 0;
		for(ProduitCommande prod : products){
			total+=prod.getPrixTotal();
		}
		return total;
	}
	
	public void setClient(Client client){
		this.client = client;
	}

	public void setSessionId(String id) {
		this.sessionId = id;
		
	}

	public String getSessionId() {
		return this.sessionId;
	}
	
	public void setProducts(HashSet<ProduitCommande> products){
		this.products = products;
	}
	
	public Set<ProduitCommande> getProducts(){
		return products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setProducts(Set<ProduitCommande> products) {
		this.products = products;
	}
	
	public Double getTaxes(){
		return (this.getTotal()/100)*15;
	}
	
	public Double getTotalWithTaxes(){
		return getTotal() + getTaxes();
	}


	public void vider() {
		
		
	}
	
}
