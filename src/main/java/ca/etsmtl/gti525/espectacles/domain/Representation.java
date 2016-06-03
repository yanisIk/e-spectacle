package ca.etsmtl.gti525.espectacles.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Representations")
public class Representation{
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nom;
	@ManyToOne
	@NotNull
	private Salle salle;
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Spectacle spectacle;
	@NotNull
	private Date dateDebut;
	@OneToOne(fetch = FetchType.EAGER)
	private Billet billet;
	
	public Representation(){
		
	}

	public Representation(Spectacle spectacle, Salle salle, Date dateDebut){
		this.spectacle = spectacle;
		this.salle = salle;
		this.dateDebut = dateDebut;
		this.nom = spectacle.getNom() + " : " + salle.getNom();
	}

	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Spectacle getSpectacle() {
		return spectacle;
	}

	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	
	public String getDateDebutFormatee() {
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm");
		
		return ft.format(dateDebut);
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public Billet getBillet(){
		return billet;
	}
	
	public void setBillet(Billet billet){
		this.billet = billet;
	}
	
	
}
