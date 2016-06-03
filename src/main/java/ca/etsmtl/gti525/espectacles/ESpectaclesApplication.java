package ca.etsmtl.gti525.espectacles;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle;
import ca.etsmtl.gti525.espectacles.domain.Representation;
import ca.etsmtl.gti525.espectacles.domain.Salle;
import ca.etsmtl.gti525.espectacles.domain.Spectacle;
import ca.etsmtl.gti525.espectacles.repositories.BilletRepository;
import ca.etsmtl.gti525.espectacles.repositories.RepresentationRepository;
import ca.etsmtl.gti525.espectacles.repositories.SalleRepository;
import ca.etsmtl.gti525.espectacles.repositories.SpectacleRepository;

@SpringBootApplication
public class ESpectaclesApplication extends SpringBootServletInitializer {

	final private static Logger logger = LoggerFactory.getLogger(ESpectaclesApplication.class);
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ESpectaclesApplication.class);
	    }
	
	
	
    public static void main(String[] args) { 
        ConfigurableApplicationContext context = SpringApplication.run(new Class[]{ESpectaclesApplication.class, Initializer.class}, args);
        
        insertDataAtStartup(context);
    }

    private static void insertDataAtStartup(
	    ConfigurableApplicationContext context) {
	//Inserting data at startup
        RepresentationRepository representationRepo = context.getBean(RepresentationRepository.class);
        SalleRepository salleRepo = context.getBean(SalleRepository.class);
        SpectacleRepository spectacleRepo = context.getBean(SpectacleRepository.class);
        BilletRepository billetRepo = context.getBean(BilletRepository.class); 
        
        Spectacle[] spectacles = {
        new Spectacle("Casse noisette", "Belle scene de théatre pour tous les âges",CategoriesSpectacle.THEATRE, "images-spectacles/casse_noisette.jpg"),
        new Spectacle("Totem", "Nature, couleurs, univers enchantant",CategoriesSpectacle.CIRQUE, "images-spectacles/cirque_soleil_1.jpg"),
        new Spectacle("Iris", "Assez spécial...",CategoriesSpectacle.CIRQUE, "images-spectacles/cirque_soleil_2.jpg"),
        new Spectacle("Cirque du soleil", "Savane, animaux etc...",CategoriesSpectacle.CIRQUE, "images-spectacles/cirque_soleil_3.jpg"),
        new Spectacle("Kurios", "Pour les curieux",CategoriesSpectacle.CIRQUE, "images-spectacles/cirque_soleil_4.jpg"),
        new Spectacle("DeadMau5", "Musique éléctronique",CategoriesSpectacle.MUSIQUE, "images-spectacles/deadmau5.jpg"),
        new Spectacle("Sugar Sammy", "En français SVP, spectacle humoristique...",CategoriesSpectacle.HUMOUR, "images-spectacles/sugar-sammy.jpg"),
        };
        
        Salle[] salles = {
        new Salle("Centre Bell", "A4422", 400, "Montreal, Canada"),
        new Salle("Quartier des spéctacles", "A4422", 400, "Montreal, Canada"),
        new Salle("Opéra de Montreal", "A4422", 400, "Montreal, Canada"),
        };
        
        Representation[] representations = {
        new Representation(spectacles[0], salles[0], new Date()),
        new Representation(spectacles[1], salles[1], new Date()),
        new Representation(spectacles[2], salles[2], new Date()),
        new Representation(spectacles[3], salles[1], new Date()),
        new Representation(spectacles[4], salles[0], new Date()),
        new Representation(spectacles[5], salles[0], new Date()),
        new Representation(spectacles[6], salles[0], new Date()),
        
        new Representation(spectacles[0], salles[1], new Date()),
        new Representation(spectacles[1], salles[2], new Date()),
        new Representation(spectacles[2], salles[1], new Date()),
        new Representation(spectacles[3], salles[2], new Date()),
        new Representation(spectacles[4], salles[2], new Date()),
        new Representation(spectacles[5], salles[2], new Date()),
        new Representation(spectacles[6], salles[1], new Date()),
        };
        
        Billet[] billets = {
        		new Billet(representations[0], (double) 45, 50),
                new Billet(representations[1], (double) 50, 50),
                new Billet(representations[2], (double) 25, 50),
                new Billet(representations[3], (double) 49, 50),
                new Billet(representations[4], (double) 89, 50),
                new Billet(representations[5], (double) 99, 50),
                new Billet(representations[6], (double) 65, 50),
                
                new Billet(representations[7], (double) 50, 50),
                new Billet(representations[8], (double) 45, 50),
                new Billet(representations[9], (double) 30, 50),
                new Billet(representations[10], (double) 39, 50),
                new Billet(representations[11], (double) 79, 50),
                new Billet(representations[12], (double) 89, 50),
                new Billet(representations[13], (double) 55, 50),
                };
        
        
        salleRepo.save(Arrays.asList(salles));
        
        spectacles[0].getRepresentations().addAll(Arrays.asList(representations[0],representations[7]));
        spectacles[1].getRepresentations().addAll(Arrays.asList(representations[1],representations[8]));
        spectacles[2].getRepresentations().addAll(Arrays.asList(representations[2],representations[9]));
        spectacles[3].getRepresentations().addAll(Arrays.asList(representations[3],representations[10]));
        spectacles[4].getRepresentations().addAll(Arrays.asList(representations[4],representations[11]));
        spectacles[5].getRepresentations().addAll(Arrays.asList(representations[5],representations[12]));
        spectacles[6].getRepresentations().addAll(Arrays.asList(representations[6],representations[13]));
        
        spectacleRepo.save(Arrays.asList(spectacles));
        billetRepo.save(Arrays.asList(billets));
        
        logger.info(LoggerMessageConstants.AJOUT_DONNEES_REPO_TERMINER);
    }
}
