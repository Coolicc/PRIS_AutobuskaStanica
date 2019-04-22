package autobuskaStanica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import autobuskaStanica.model.Korisnik;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.model.Ulogakorisnka;
import autobuskaStanica.repository.KorisnikJPARepo;
import autobuskaStanica.repository.PrevoznikJPARepo;
import autobuskaStanica.repository.UlogaKorisnikaJPARepo;

@Controller
@RequestMapping(value="/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikJPARepo kjr;
	
	@Autowired
	UlogaKorisnikaJPARepo ujr;
	
	@Autowired
	PrevoznikJPARepo pjr;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model m) {
		Korisnik user = (Korisnik) kjr.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
		if (user !=null) {
	    	request.getSession().setAttribute("user", user);
	    } else {
	    	m.addAttribute("message", "Username ili password nisu korektni!");
	    	return "login";
	  	}
		return "index";
	}
	
	@RequestMapping(value="loginPage")
    public String index(Model model) {
        return "login";
    }
	
	@RequestMapping(value="registracija", method=RequestMethod.GET)
	public String vratiSveUloge(Model m, HttpServletRequest request) {
		List<Ulogakorisnka> uloge = ujr.findAll();
		request.getSession().setAttribute("uloge",uloge);
		return "register";
	}
	
	@RequestMapping(value="registracija" , method=RequestMethod.POST)
	public String registrujKorisnika(Model m, @Valid Korisnik korisnik, BindingResult bindingResult)  {
		
		if (bindingResult.hasErrors()) {
			for(FieldError f : bindingResult.getFieldErrors()) {
					m.addAttribute(f.getField()+"err", f.getDefaultMessage() );	
			}
			
			m.addAttribute("ime", korisnik.getIme());
			m.addAttribute("prezime", korisnik.getPrezime());
			m.addAttribute("username", korisnik.getUsername());
		
			return "register";
		} else {
			Korisnik  sacuvan =  kjr.save(korisnik);
			if(sacuvan != null)
				m.addAttribute("message", "Uspesna registracija! Mozete se ulogovati!");
			else
				m.addAttribute("message", "Registracija nije uspela!Pokusajte ponovo");
			return "login";
		}
	}
	
	@RequestMapping(value="prevoznici", method=RequestMethod.GET)
	public String prevoznici(Model m) {
		List<Prevoznik> prevoznici = pjr.findAll();
		m.addAttribute("prevoznici", prevoznici);
		return "prevoznici";
	}
	
	@RequestMapping("odjava")
	public String odjavljivanje(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "index";
	}
	
}
