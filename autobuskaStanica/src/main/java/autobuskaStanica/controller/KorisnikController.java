package autobuskaStanica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import autobuskaStanica.model.Korisnik;
import autobuskaStanica.repository.KorisnikJPARepo;

@Controller
@RequestMapping(value="/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikJPARepo kjr;
	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model m) {
		Korisnik user = (Korisnik) kjr.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
			//validateUser(login);
		if (user !=null) {
	    	m.addAttribute("user", user);
	    	//cuvanje korisnika u sesiji?	
	    } else {
	    	m.addAttribute("message", "Username ili password nisu korektni!");
	    	return "login";
	  	}
		 return "welcome";
	}
	
	@RequestMapping(value="loginPage")
    public String index(Model model) {

        return "login";
    }
	
}
