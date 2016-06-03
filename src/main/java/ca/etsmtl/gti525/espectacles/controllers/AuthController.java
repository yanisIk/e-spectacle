package ca.etsmtl.gti525.espectacles.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.etsmtl.gti525.espectacles.domain.Client;
import ca.etsmtl.gti525.espectacles.domain.User;
import ca.etsmtl.gti525.espectacles.repositories.ClientRepository;

@Controller
@Scope("session")
public class AuthController {
	  
	@Autowired
	private Client client;
	@Autowired
	private ClientRepository clientRepo;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session){
		//logout user
		if(client != null){
			session.invalidate();
		}
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request){
		boolean success=false;
		//login user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == null || password == null){
			success = false;
		}
		else if(username.trim().isEmpty() || password.trim().isEmpty()){
			success = false;
		}
		else{
			Client stubClient = clientRepo.findByUsername(username);
			String hashedPassword=null;
			if(client.getPasswordHash().equals(hashedPassword)){
				success=true;
				client = stubClient;
			}
		}
		
		if(success){
			return "redirect: ";	
		}
		else{
			return "redirect:/login?error";
		}
			
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Model model, HttpServletRequest request){
		boolean success=false;
		//create user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		if(username == null || password == null){
			success = false;
		}
		else if(username.trim().isEmpty() || password.trim().isEmpty()){
			success = false;
		}
		else{
			Client newClient = new Client();
			newClient.setUsername(username);
			newClient.setNom(username);
			newClient.setPasswordHash(password);
			newClient.setEmail(email);
			
			try{
				clientRepo.save(newClient);
				success = true;
				this.client = newClient;
			}
			catch(Exception e){
				success = false;
			}
			
		}
		
		
		if(success){
			return "redirect: ";	
		}
		else{
			return "redirect:/login?error=signup";
		}
	}
}
