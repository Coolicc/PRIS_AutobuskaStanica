package autobuskaStanica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import autobuskaStanica.model.Korisnik;
import autobuskaStanica.model.Ulogakorisnka;
import autobuskaStanica.repository.KorisnikJPARepo;
import autobuskaStanica.repository.UlogaKorisnikaJPARepo;

@Controller
@RequestMapping(value="/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikJPARepo kjr;
	
	@Autowired
	UlogaKorisnikaJPARepo ujr;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model m) {
		Korisnik user = (Korisnik) kjr.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
			//validateUser(login);
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
	
	@RequestMapping(value="sveUloge", method=RequestMethod.GET)
	public String vratiSveUloge(Model m) {
		List<Ulogakorisnka> uloge = ujr.findAll();
		m.addAttribute("uloge",uloge);
		return "registracija";
	}
	
	@RequestMapping(value="registracija" , method=RequestMethod.POST)
	public String registrujKorisnika(String ime, String prezime, String username, String password,String ulogakorisnika, Model m)  {//promeni
		System.out.println("ime"+ ime);
		Ulogakorisnka uloga = ujr.findById(Integer.parseInt(ulogakorisnika)).get();
		Korisnik k = new Korisnik();
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setUsername(username);
		k.setPassword(password);
		k.setUlogakorisnka(uloga);
		
		Korisnik  sacuvan =  kjr.save(k);
		if(sacuvan != null)
			m.addAttribute("message", "Uspesna registracija! Mozete se ulogovati!");
		else
			m.addAttribute("message", "Registracija nije uspela!Pokusajte ponovo");
		return "index";
	}
	
}
