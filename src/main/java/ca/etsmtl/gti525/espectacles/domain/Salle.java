package ca.etsmtl.gti525.espectacles.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Salles")
public class Salle {

	@Id
    @GeneratedValue
    private Long id;
	
	@NotNull
	private String nom;
	@NotNull
	private String numero;
	private int nbPlaces;
	@OneToMany
	private Set<Representation> representations = new HashSet<>();
	@NotNull
	private String address;
	
	public Salle(){
		
	}
	
	public Salle(String nom, String numero, int nbPlaces, String address){
		this.nom = nom;
		this.numero = numero;
		this.nbPlaces = nbPlaces;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}
	
	public String getDisponibilite() {
		
		String disponibilite;
		
		if (this.getNbPlaces() > 0) {
			disponibilite = " Disponible";
		}
		else {
			disponibilite = " Non disponible";
		}
		
		return disponibilite;
	}
	

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Set<Representation> getRepresentations() {
		return representations;
	}

	public void setRepresentations(Set<Representation> representations) {
		this.representations = representations;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
