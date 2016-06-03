/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.etsmtl.gti525.espectacles.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Entity
@Table(name = "Factures")
@Component
@Scope("session")
public class Facture {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;
	@ManyToOne(fetch = FetchType.EAGER)
	private Guest guest;
	@OneToOne(fetch = FetchType.EAGER)
	private Paiement paiement;
	@NotNull
	private String addresseLivraison;
	@NotNull
	private String addresseFacturation;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProduitCommande> produitsCommandes = new HashSet<ProduitCommande>();

	private String numeroCommande;

	public Facture(Client client, Set<ProduitCommande> produitsCommandes) {
		this(client, client.getAddresseLivraison(), client.getAddresseFacturation(), produitsCommandes);
	}


	public Facture(Client client, String shippingAddress, String billingAddress, Set<ProduitCommande> produitsCommandes) {

		Assert.notNull(client, "Le client ne doit pas etre null");
		Assert.notNull(shippingAddress, "L'addresse de livraison ne doit pas etre null");

		this.client = client;
		this.addresseLivraison = shippingAddress;
		this.addresseFacturation = billingAddress;
		this.produitsCommandes = produitsCommandes;
	}

	public Facture() {

	}

	
	public void add(ProduitCommande ProduitCommande) {
		this.produitsCommandes.add(ProduitCommande);
	}

	
	public Client getClient() {
		return client;
	}

	
	public String getBillingAddress() {
		return addresseFacturation != null ? addresseFacturation : addresseLivraison;
	}

	
	public String getShippingAddress() {
		return addresseLivraison;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Paiement getPaiement() {
		return paiement;
	}


	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}


	public String getAddresseLivraison() {
		return addresseLivraison;
	}


	public void setAddresseLivraison(String addresseLivraison) {
		this.addresseLivraison = addresseLivraison;
	}


	public String getAddresseFacturation() {
		return addresseFacturation;
	}


	public void setAddresseFacturation(String addresseFacturation) {
		this.addresseFacturation = addresseFacturation;
	}


	public Set<ProduitCommande> getProduitsCommandes() {
		return produitsCommandes;
	}


	public void setProduitsCommandes(Set<ProduitCommande> produitsCommandes) {
		this.produitsCommandes = produitsCommandes;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Guest getGuest() {
		return guest;
	}


	public void setGuest(Guest guest) {
		this.guest = guest;
		this.addresseFacturation = guest.getAddresseFacturation();
		this.addresseLivraison = guest.getAddresseLivraison();
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
		
	}
	
	public String getNumeroCommande(){
		return numeroCommande;
	}

	public double getTotal() {

		double total = 0;

		for (ProduitCommande produitCommande : produitsCommandes) {
			total += produitCommande.getPrixTotal();
		}

		return total;
	}


	
}

