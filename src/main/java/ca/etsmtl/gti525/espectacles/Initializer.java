package ca.etsmtl.gti525.espectacles;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;

public class Initializer implements ServletContextInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    servletContext.addListener(new WebSessionListener());
	}	
}
