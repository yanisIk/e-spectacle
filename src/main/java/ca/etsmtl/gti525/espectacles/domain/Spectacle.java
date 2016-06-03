package ca.etsmtl.gti525.espectacles.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Spectacles")
public class Spectacle {

	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
	@NotNull
	private String nom;
	
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Representation> representations = new ArrayList<Representation>();
	
	@Enumerated(EnumType.STRING)
    private CategoriesSpectacle categorie;
	
	/*
	 * example : shop/product9.jpg
	 * in the jsp it will render as : 
	 * <img src="${pageContext.request.contextPath}/resources/images/${spectacle.imageUrl}"
	 */
	private String imageUrl;
	
	public Spectacle(){
		
	}

	//Constructor for bootstraping data
	public Spectacle(String nom, String description, CategoriesSpectacle categorie, String imageUrl){
		this.nom = nom;
		this.description = description;
		this.setImageUrl(imageUrl);
		this.categorie = categorie;
	}
	
	public Long getId(){
		return id;
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


	public List<Representation> getRepresentations() {
		return representations;
	}

	public String getImageUrl(){
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public CategoriesSpectacle getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriesSpectacle categorie) {
		this.categorie = categorie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRepresentations(List<Representation> representations) {
		this.representations = representations;
	}
	
	
	
	
}
