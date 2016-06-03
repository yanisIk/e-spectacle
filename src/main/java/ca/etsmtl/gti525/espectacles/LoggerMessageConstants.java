package ca.etsmtl.gti525.espectacles;

public class LoggerMessageConstants {
	
    	//EspectaclesApplication
  	public static final String AJOUT_DONNEES_REPO_TERMINER = "Les repositoires ont été créés et remplis.";
  	
    	//Panier Jobs & Panier Service
	public static final String PANIER_VIDER_SESSION_DESTROYED = "Le panier a été vidé par la destruction de la session.";
	public static final String PANIER_VIDER_SESSION_TIMEOUT = "Le panier a été vidé par PanierJobs";
	public static final String TIMER_SESSION_INIT = "Le timer de session a été initialisé.";

	// Homepage Controller
	public static final String AJOUT_LISTE_SPECTACLES_INDEX = "La liste des spectacles a été injectée dans la page index.jsp.";
	
	// Panier Controller
	public static final String CONSULTER_PANIER = "Ouverture de la page cart.jsp.";
	public static final String CHECKOUT_PANIER = "Checkout du panier.";
	public static final String AJOUT_PRODUIT_PANIER_DECREMENTER_QUANTITE_BILLET = 
		"Produit ajouté au panier et décrémenté du billet qui contrôle la quantité de produits disponibles."
		+ " Nom du produit et quantité : ";
	public static final String ENLEVE_PRODUIT_PANIER_INCREMENTE_QUANTITE_BILLET = 
		"Produit enlevé du panier et incrémenté du billet qui contrôle la quantité de produits disponibles."
		+ " Nom du produit et quantité : ";
	
	//SpectacleController
	public static final String AJOUT_LISTE_SPECTACLES_SHOP = "La liste des spectacles a été ajoutée comme attribut dans la page shop.jsp.";
	public static final String AJOUT_BILLER_SPECTACLE_PRODUCT_DETAILS = "Le spectacle choisi et le billet correspondant ont été ajoutées comme attributs dans la page product-details.jsp.";
	public static final String AJOUT_SPECTACLES_TROUVES_RECHERCHE = "L'action de la recherche de spectacles a injecté la liste des spectacles visée comme attribut dans la page products.jsp.";
	public static final String AJOUT_REPRESENTATION_GET_REPRESENTATION = "L'action de la consultation des représentations d'un spectacle a injecté la représentation et le billet correspondant dans la page product-details.jsp";
	public static final String AJOUT_LISTE_SPECTACLE_CATEGORIE = "La liste de spectacles de la catégorie choisie a été ajoutée comme attribut dans la page shop.jsp";
	
	//BilletService
	public static final String BILLET_RECHERCHE_PAR_ID = "Recherche de billets par l'identificateur du billet : ";
	public static final String BILLET_RECHERCHE_PAR_REPRESENTATION_ID = "Recherche de billets par l'identificateur de la représentation : ";
	public static final String BILLET_SAUVEGARDE_REPO = "État du billet sauvegardé, ID : ";
	
	//RepresentationService
    	public static final String REPRESENTATION_GET_ALL= "Recherche de tous les représentations.";
    	public static final String REPRESENTATION_GET_WITH_PRODUCT_ID= "Recherche de représentation par l'ID du produit : ";
    	public static final String REPRESENTATION_GET_WITH_NAME = "Recherche de représentation par le nom du produit : ";
    	
    	//SpectacleService
    	public static final String SPECTACLES_GET_ALL= "Recherche de tous les spectacles.";
    	public static final String SPECTACLES_GET_WITH_SPECTACLE_NAME= "Recherche du spectacle par le nom du spectacle : ";
    	public static final String SPECTACLES_GET_FEATURED = "Recherche des spectacle du moment";
    	public static final String SPECTACLES_GET_BY_CATEGORY = "Recherche des spectacle par catégorie : ";
}
