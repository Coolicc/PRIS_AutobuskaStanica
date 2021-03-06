package autobuskaStanica.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import autobuskaStanica.beans.StanicaBean;
import autobuskaStanica.model.Destinacija;
import autobuskaStanica.model.Karta;
import autobuskaStanica.model.Komentar;
import autobuskaStanica.model.Korisnik;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.model.Ruta;
import autobuskaStanica.model.Stanica;
import autobuskaStanica.model.Ulogakorisnka;
import autobuskaStanica.model.Vrstakarte;
import autobuskaStanica.repository.DestinacijaJPARepo;
import autobuskaStanica.repository.KartaJPARepo;
import autobuskaStanica.repository.KomentarJPARepo;
import autobuskaStanica.repository.KorisnikJPARepo;
import autobuskaStanica.repository.PrevoznikJPARepo;
import autobuskaStanica.repository.StanicaJPARepo;
import autobuskaStanica.repository.UlogaKorisnikaJPARepo;
import autobuskaStanica.repository.VrstaKarteJPARepo;
import autobuskaStanica.service.PolasciService;


@Controller
@RequestMapping(value="/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikJPARepo kjr;
	
	@Autowired
	KomentarJPARepo komentarJPARepo;
	
	@Autowired
	UlogaKorisnikaJPARepo ujr;
	
	@Autowired
	PrevoznikJPARepo pjr;
	
	@Autowired
	DestinacijaJPARepo destinacijaJpaRepo;
	
	@Autowired
	VrstaKarteJPARepo vrstaKarteJpaRepo;
	
	@Autowired
	StanicaJPARepo stanicaJpaRepo;
	
	@Autowired
	KartaJPARepo kartaJpaRepo;
	
	@Autowired
	PolasciService polasciService;
	
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String indexPage(HttpServletRequest request, Model m) {
		List<StanicaBean> sbeans = new ArrayList<>();
		List<Stanica> stanice = stanicaJpaRepo.getNajpovoljnijePrevoze(new PageRequest(0, 10));
		for (Stanica s: stanice) {
			StanicaBean sb = new StanicaBean();
			Ruta r = s.getRuta();
			sb.setRutaID(r.getRutaID());
			sb.setVrstaKarte(4);
			sb.setPrevoznik(r.getPrevoznikBean().getNaziv());
			sb.setCena(s.getCena());
			sb.setPolazak(s.getPolazak());
			sb.setOdDestinacija(s.getDestinacija().getGrad() + ", " + s.getDestinacija().getDrzava());
			sb.setPolazakDest(s.getDestinacija().getDestinacijaID());
			Stanica next = s.getRuta().getStanicas().get(s.getBrStanice() + 1);
			sb.setDoDestinacija(next.getDestinacija().getGrad() + ", " + next.getDestinacija().getDrzava());
			sb.setDolazak(next.getDolazak());
			sb.setDolazakDest(next.getDestinacija().getDestinacijaID());
			sbeans.add(sb);
		}
		m.addAttribute("stanice", sbeans);
		return "index";
	}
	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model m) {
		Korisnik user = (Korisnik) kjr.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
		if (user !=null) {
	    	request.getSession().setAttribute("user", user);
	    	request.getSession().setAttribute("uloga", user.getUlogakorisnka());
	    } else {
	    	m.addAttribute("message", "Username ili password nisu korektni!");
	    	return "login";
	  	}
		return "redirect:/korisnik/initPocetna";
	}
	
	@RequestMapping(value="loginPage")
    public String index(Model model) {
        return "login";
    }
    
	
	@RequestMapping(value="registracijaPage", method=RequestMethod.GET)
	public String registracija() {
		return "register";
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
		request.getSession().removeAttribute("uloga");
		return "redirect:/korisnik/index";
	}
	
	//komentari
	
	@RequestMapping(value="komentari")
    public String komentari(HttpServletRequest request, Model m, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
			return "redirect:/korisnik/initPocetna";
		}
        return "komentari";
    }
	
	@RequestMapping(value="komentariZaPrevoznika/{prevoznikID}")
    public String komentariZaPrevoznika(Model m, @PathVariable("prevoznikID") int prevoznikID, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
			return "redirect:/korisnik/initPocetna";
		}
		Prevoznik p = (Prevoznik) pjr.findById(prevoznikID).get();
		m.addAttribute("p", p);
		List<Komentar> komentariZaPrevoz = komentarJPARepo.vratiKomentareZaPrevoznika(prevoznikID);
		m.addAttribute("komentariZaPrevoz", komentariZaPrevoz);
        return "komentariZaPrevoznika";
    }
	
	@RequestMapping(value="saveKomentarZaPrevoznika/{prevoznikID}", method=RequestMethod.POST)
	public String saveKomentarZaPrevoznika(Model m, @PathVariable("prevoznikID") int prevoznikID, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			Korisnik user = (Korisnik)request.getSession().getAttribute("user");
			if(user==null) {
				return "redirect:/korisnik/loginPage";
			}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
				redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
				return "komentariZaPrevoznika";
			}
			Komentar k = new Komentar();
			k.setKomentar(request.getParameter("komentar"));
			Prevoznik p = pjr.findById(prevoznikID).get();
			p.addKomentar(k);
			k.setKorisnik(user);
			pjr.saveAndFlush(p);
			komentarJPARepo.save(k);
			List<Komentar> komentari = komentarJPARepo.vratiKomentareZaPrevoznika(prevoznikID);
			//m.addAttribute("komentari", komentari);
			redirectAttributes.addFlashAttribute("komentari", komentari);
			redirectAttributes.addFlashAttribute("message", "Komentar je uspesno dodat.");
			
			} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/korisnik/komentariZaPrevoznika/{prevoznikID}";
	}
	
	@RequestMapping(value = "initRezervacija", method = RequestMethod.GET)
	public String initRezervacija(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da rezervisete");
			return "redirect:/korisnik/initPocetna";
		}
		List<Destinacija> destinacije = destinacijaJpaRepo.findAll();
		List<Vrstakarte> vrsteKarata = vrstaKarteJpaRepo.findAll();
		
		m.addAttribute("destinacije", destinacije);
		m.addAttribute("vrsteKarata", vrsteKarata);
		return "rezervisanje";
	}
	
	@RequestMapping(value = "prikazPolazaka", method = RequestMethod.GET)
	public String rezervacija(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("user");
		if(k == null) {
			return "redirect:/korisnik/loginPage";
		}
		if(!k.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			return "redirect:/korisnik/initPocetna";
		}
		int destPolazak = Integer.parseInt(request.getParameter("polazak"));
		int destDolazak = Integer.parseInt(request.getParameter("dolazak"));
		int vrstaKarte = Integer.parseInt(request.getParameter("vrstaKarte"));
		//treba da se proverava da li postoji polazak odredjenog datuma
		//za sad ne radi nista sa datumom
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date datumPolaska;
		try {
			datumPolaska = sdf.parse(request.getParameter("datumPolaska"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(vrstaKarte == 0) {
			vrstaKarte = 4;
		}
		if(destPolazak==0 || destDolazak==0) {
			redirectAttributes.addFlashAttribute("msg", "Morate izabrati destinaciju za polazak i dolazak");
			return "redirect:/korisnik/initRezervacija";
		}
		
		List<Ruta> rezRuta = polasciService.ruteSaDestinacijama(destPolazak, destDolazak);
		
		List<Double> cene = polasciService.ceneZaPolaske(rezRuta, destPolazak, destDolazak, vrstaKarte);
		
		m.addAttribute("rezRuta", rezRuta);
		m.addAttribute("destPolazak", destPolazak);
		m.addAttribute("destDolazak",destDolazak);
		m.addAttribute("cene", cene);
		m.addAttribute("vrstaKarte", vrstaKarte);
		return "prikazPolazakaZaRezervaciju";
	}
	
	@RequestMapping(value="rezervisi", method=RequestMethod.GET)
	public String potvrdaRezervacije(@RequestParam("polazakDest") int polazakDest, @RequestParam("dolazakDest") int dolazakDest, @RequestParam("vrstaKarte") int vrstaKarte, @RequestParam("ruta") int rutaID,@RequestParam("cena") double cena,  Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da rezervisete");
			return "redirect:/korisnik/initPocetna";
		}
		
		Karta k= new Karta();
		
		Stanica sPolazna = stanicaJpaRepo.findByDestinacijaIDAndRutaID(rutaID, polazakDest);
		Stanica sDolazna = stanicaJpaRepo.findByDestinacijaIDAndRutaID(rutaID, dolazakDest);
		List<Stanica> medjuStanice = stanicaJpaRepo.getMedjustaniceNaRuti(sPolazna.getBrStanice(), sDolazna.getBrStanice(), rutaID);
		boolean ok = true;
		for(Stanica s: medjuStanice) {
			if(s.getBrSlobodnihMesta()-1<=0) {
				m.addAttribute("err","Nazalost nema mesta");
				ok=false;
				return "potvrdaRezervacije";
			}
		}
		if(ok) {
			for(Stanica s: medjuStanice) {
				s.setBrSlobodnihMesta(s.getBrSlobodnihMesta()-1);
				stanicaJpaRepo.save(s);
			}
		}
		
		
		k.setDatumRezervacije(new Date());
		k.setVrstakarte(vrstaKarteJpaRepo.findByVrstaKarteID(vrstaKarte));
		k.setKorisnik2(user);
		List<Karta> karteKorisnika = kartaJpaRepo.rezervacijeKorisnika(user.getKorisnikID());
		if((karteKorisnika.size()+1)%3==0 ) {
			String msg = "Dobijate popust 10% za ovu rezervaciju!";
			k.setKonacnaCena(cena*0.9);
			m.addAttribute("msgPopust", msg);
		}else {
			k.setKonacnaCena(cena);
		}
		k.setStanica2(sPolazna);
		k.setStanica1(sDolazna);
		kartaJpaRepo.save(k);
		m.addAttribute("msgRez","Uspesno ste rezervisali");
		return "potvrdaRezervacije";
	}
	
	@RequestMapping(value="prevozniciZaKomentar", method=RequestMethod.GET)
	public String getPrevoznici(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
			return "redirect:/korisnik/initPocetna";
		}
		List<Prevoznik> prevozniciZaKomentar = pjr.findAll();
		m.addAttribute("prevozniciZaKomentar", prevozniciZaKomentar);
		return "komentari";
	}
	
	//komentari za prevoznika iz dropdown
	@RequestMapping(value="getKomentari", method=RequestMethod.GET)
	public String getKomentari(HttpServletRequest request, Model m, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
			return "redirect:/korisnik/initPocetna";
		}
		List<Komentar> komentari = komentarJPARepo.vratiKomentareZaPrevoznika(Integer.parseInt(request.getParameter("prevoznik")));
		m.addAttribute("komentari", komentari);
		return "saveKomentar";
	}
	
	@RequestMapping(value="saveKomentar", method=RequestMethod.POST)
	public String saveKomentar(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			Korisnik user = (Korisnik)request.getSession().getAttribute("user");
			if(user==null) {
				return "redirect:/korisnik/loginPage";
			}else if(!user.getUlogakorisnka().getNazivUloge().equals("PUTNIK")) {
				redirectAttributes.addFlashAttribute("autherr", "Niste putnik, ne mozete da komentarisete");
				return "redirect:/korisnik/initPocetna";
			}
			Komentar k = new Komentar();
			k.setKomentar(request.getParameter("komentar"));
			Prevoznik p = pjr.findById(Integer.parseInt(request.getParameter("prevoznik"))).get();
			p.addKomentar(k);
			k.setKorisnik(user);
			pjr.saveAndFlush(p);
			komentarJPARepo.save(k);
			List<Komentar> komentari = komentarJPARepo.vratiKomentareZaPrevoznika(Integer.parseInt(request.getParameter("prevoznik")));
			//m.addAttribute("komentari", komentari);
			redirectAttributes.addFlashAttribute("komentari", komentari);
			redirectAttributes.addFlashAttribute("message", "Komentar je uspesno dodat.");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/korisnik/prevozniciZaKomentar";
	}
	
	
	@RequestMapping(value="initPocetna", method=RequestMethod.GET)
	public String initPocetna(Model m, HttpServletRequest request) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}
		
		List<Destinacija> destinacije = destinacijaJpaRepo.findAll();
		List<Vrstakarte> vrsteKarata = vrstaKarteJpaRepo.findAll();
		
		m.addAttribute("destinacije", destinacije);
		m.addAttribute("vrsteKarata", vrsteKarata);
		return "pocetna";
		
	}
	
	@RequestMapping(value="pocetna", method=RequestMethod.GET)
	public String prikazPolazaka(Model m,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}
		initPocetna(m,request);
		int destPolazak = Integer.parseInt(request.getParameter("polazak"));
		int destDolazak = Integer.parseInt(request.getParameter("dolazak"));
		int vrstaKarte = Integer.parseInt(request.getParameter("vrstaKarte"));
		//provera za datum
		
		if(vrstaKarte == 0) {
			vrstaKarte = 4;
		}
		if(destPolazak==0 || destDolazak==0) {
			redirectAttributes.addFlashAttribute("msg", "Morate izabrati destinaciju za polazak i dolazak");
			return "redirect:/korisnik/initPocetna";
		}
		
		List<Ruta> rute= polasciService.ruteSaDestinacijama(destPolazak, destDolazak);
		
		List<Double> cene = polasciService.ceneZaPolaske(rute, destPolazak, destDolazak, vrstaKarte);
		
		m.addAttribute("rute", rute);
		m.addAttribute("destPolazak", destPolazak);
		m.addAttribute("destDolazak",destDolazak);
		m.addAttribute("cene", cene);
		m.addAttribute("vrstaKarte", vrstaKarte);
		return "pocetna";
	}
	
	@RequestMapping(value = "initProdajaKarata", method = RequestMethod.GET)
	public String initProdajaKarata(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("RADNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste radnik, ne mozete da prodajete karte");
			return "redirect:/korisnik/initPocetna";
		}
		return "prodajaKarata";
	}
	
	@RequestMapping(value = "pronadjiRezervaciju", method = RequestMethod.GET)
	public String pronadjiRezervaciju(Model m, HttpServletRequest request,String ime, String prezime, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("RADNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste radnik, ne mozete prodajete karte");
			return "redirect:/korisnik/initPocetna";
		}

		Korisnik k = kjr.findByImeAndPrezime(ime, prezime);
		if(k == null) {
			m.addAttribute("msg", "Ne postoji korisnik sa zadatim imenom");
			return "prodajaKarata";
		}
		
		List<Karta> rezervacije = kartaJpaRepo.rezervacijeKorisnika(k.getKorisnikID());
		m.addAttribute("rezervacije", rezervacije);
		
		return "prodajaKarata";
	}
	
	@RequestMapping(value = "prodajKartu", method = RequestMethod.POST)
	public String prodajKartu(Model m, HttpServletRequest request, int idKarte, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("RADNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste radnik, ne mozete prodajete karte");
			return "redirect:/korisnik/initPocetna";
		}
		
		Karta k = kartaJpaRepo.findById(idKarte);
		k.setDatumProdaje(new Date());
		Korisnik radnik = (Korisnik) request.getSession().getAttribute("user");
		k.setKorisnik1(radnik);
		kartaJpaRepo.save(k);
		m.addAttribute("prodataKarta", "Uspesno prodata karta");
		
		return "prodajaKarata";
	}
	
	//trenutno modifikovano od rezervisi
	@RequestMapping(value="kupiBezRezervacije", method=RequestMethod.GET)
	public String kupovinaBezRezervacije(@RequestParam("polazakDest") int polazakDest, @RequestParam("dolazakDest") int dolazakDest, @RequestParam("vrstaKarte") int vrstaKarte, @RequestParam("ruta") int rutaID,@RequestParam("cena") double cena,  Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("RADNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste radnik, ne mozete prodajete karte");
			return "redirect:/korisnik/initPocetna";
		}
		
		Karta k= new Karta();
		
		Stanica sPolazna = stanicaJpaRepo.findByDestinacijaIDAndRutaID(rutaID, polazakDest);
		Stanica sDolazna = stanicaJpaRepo.findByDestinacijaIDAndRutaID(rutaID, dolazakDest);
		List<Stanica> medjuStanice = stanicaJpaRepo.getMedjustaniceNaRuti(sPolazna.getBrStanice(), sDolazna.getBrStanice(), rutaID);
		boolean ok = true;
		for(Stanica s: medjuStanice) {
			if(s.getBrSlobodnihMesta()-1<=0) {
				m.addAttribute("err","Nazalost nema mesta");
				ok=false;
				return "potvrdaRezervacije";
			}
		}
		if(ok) {
			for(Stanica s: medjuStanice) {
				s.setBrSlobodnihMesta(s.getBrSlobodnihMesta()-1);
				stanicaJpaRepo.save(s);
			}
		}
		
		Korisnik prodavac = (Korisnik) request.getSession().getAttribute("user");
		k.setDatumProdaje(new Date());
		k.setVrstakarte(vrstaKarteJpaRepo.findByVrstaKarteID(vrstaKarte));
		k.setKorisnik1(prodavac);
/* Prodajemo kartu nekome ko nema profil?!
		List<Karta> karteKorisnika = kartaJpaRepo.rezervacijeKorisnika(prodavac.getKorisnikID());
		if((karteKorisnika.size()+1)%3==0 ) {
			String msg = "Dobijate popust 10% za ovu rezervaciju!";
			k.setKonacnaCena(cena*0.9);
			m.addAttribute("msgPopust", msg);
		}else {
			k.setKonacnaCena(cena);
		}
		*/
		k.setKonacnaCena(cena);
		k.setStanica2(sPolazna);
		k.setStanica1(sDolazna);
		kartaJpaRepo.save(k);
		m.addAttribute("msgRez","Uspesno ste prodali kartu");
		return "potvrdaRezervacije";
	}
}
