package autobuskaStanica.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import autobuskaStanica.model.Destinacija;
import autobuskaStanica.model.Karta;
import autobuskaStanica.model.Korisnik;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.model.Ruta;
import autobuskaStanica.model.Stanica;
import autobuskaStanica.model.Tippolaska;
import autobuskaStanica.repository.DestinacijaJPARepo;
import autobuskaStanica.repository.KartaJPARepo;
import autobuskaStanica.repository.PrevoznikJPARepo;
import autobuskaStanica.repository.RutaJPARepo;
import autobuskaStanica.repository.StanicaJPARepo;
import autobuskaStanica.repository.TipRuteJPARepo;

@Controller
@RequestMapping(value="/admin")
@RolesAllowed("ROLE_ADMIN")
public class AdminController {
	
	private static final String SEZONSKA_ID = "3";

	@Autowired
	RutaJPARepo rutaJPARepo;
	
	@Autowired
	TipRuteJPARepo tipRuteJPARepo;
	
	@Autowired
	PrevoznikJPARepo prevoznikJPARepo;
	
	@Autowired
	DestinacijaJPARepo destinacijaJPARepo;
	
	@Autowired
	StanicaJPARepo stanicaJPARepo;
	
	@Autowired
	KartaJPARepo kartaJPARepo;
	
	
	@RequestMapping(value="initUnosRute", method=RequestMethod.GET)
	public String initUnosRute(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("ADMIN")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste admin, ne mozete da unosite rute");
			return "redirect:/korisnik/initPocetna";
		}
		List<Tippolaska> tipoviRute = tipRuteJPARepo.findAll();
		List<Prevoznik> prevoznici = prevoznikJPARepo.findAll();
		m.addAttribute("tipoviRute", tipoviRute);
		m.addAttribute("prevoznici", prevoznici);
		request.setAttribute("message", m.asMap().get("message"));
		return "unosRute";
	}
	
	@RequestMapping(value="unosRute", method=RequestMethod.POST)
	public String unosRute(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("ADMIN")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste admin, ne mozete da unosite rute");
			return "redirect:/korisnik/initPocetna";
		}
		List<Destinacija> destinacije = destinacijaJPARepo.findAll();
		Integer brojMesta = 0;
		Integer brojStanica = 0;
		try {
			brojMesta = Integer.parseInt(request.getParameter("brojMesta"));
			brojStanica = Integer.parseInt(request.getParameter("brojStanica"));
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("message", "Broj mesta i broj stanica moraju da budu broj.");
			return "redirect:/admin/initUnosRute";
		}
		m.addAttribute("tipRute", request.getParameter("tipRute"));
		m.addAttribute("prevoznik", request.getParameter("prevoznik"));
		m.addAttribute("brojMesta", brojMesta);
		m.addAttribute("brojStanica", brojStanica);
		m.addAttribute("destinacije", destinacije);
		if (request.getParameter("tipRute").equals(SEZONSKA_ID))
			m.addAttribute("sezonska", "sezonska");
		return "unosStanica";
	}
	
	@RequestMapping(value="unosStanica", method=RequestMethod.POST)
	public String unosStanica(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("ADMIN")) {
			redirectAttributes.addFlashAttribute("autherr", "Niste admin, ne mozete da unosite rute");
			return "redirect:/korisnik/initPocetna";
		}
		try {
			Ruta r = new Ruta();
			r.setBrMesta(Integer.parseInt(request.getParameter("brojMesta")));
			Prevoznik p = prevoznikJPARepo.findById(Integer.parseInt(request.getParameter("prevoznik"))).get();
			Tippolaska tp = tipRuteJPARepo.findById(Integer.parseInt(request.getParameter("tipRute"))).get();
			r.setPrevoznikBean(p);
			r.setTippolaska(tp);
			if (request.getParameter("datumVazenja") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datumVazenja;
				try {
					datumVazenja = sdf.parse(request.getParameter("datumVazenja"));
					r.setDatum(datumVazenja);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				r.setDatum(new Date());
			}
			p.addRuta(r);
			tp.addRuta(r);
			int brojStanica = Integer.parseInt(request.getParameter("brojStanica"));
			List<Stanica> stanice = new ArrayList<>();
			List<Destinacija> destinacije = new ArrayList<>();
			for (int i = 0; i < brojStanica; i++) {
				Stanica s = new Stanica();
				Destinacija d = destinacijaJPARepo.findById(Integer.parseInt(request.getParameter("stanica"+i))).get();
				s.setRuta(r);
				s.setDestinacija(d);
				d.addStanica(s);
				s.setCena(Integer.parseInt(request.getParameter("cena"+i)));
				s.setBrStanice(i);
				s.setBrSlobodnihMesta(r.getBrMesta());
				String polazakH = request.getParameter("polazakH"+i).trim();
				String polazakM = request.getParameter("polazakM"+i).trim();
				String dolazakH = request.getParameter("dolazakH"+i).trim();
				String dolazakM = request.getParameter("dolazakM"+i).trim();
				if (!polazakH.equals("") && !polazakM.equals("")) {
					int polazakHInt = Integer.parseInt(polazakH);
					int polazakMInt = Integer.parseInt(polazakM);
					if (polazakHInt < 0 || polazakHInt > 23 || polazakMInt < 0 || polazakMInt > 59) {
						throw new NumberFormatException();
					}
					Calendar polazak = Calendar.getInstance();
					polazak.set(Calendar.HOUR_OF_DAY, polazakHInt);
					polazak.set(Calendar.MINUTE, polazakMInt);
					polazak.set(Calendar.SECOND, 0);
					s.setPolazak(polazak.getTime());
				}
				if (!dolazakH.equals("") && !dolazakM.equals("")) {
					int dolazakHInt = Integer.parseInt(dolazakH);
					int dolazakMInt = Integer.parseInt(dolazakM);
					if (dolazakHInt < 0 || dolazakHInt > 23 || dolazakMInt < 0 || dolazakMInt > 59) {
						throw new NumberFormatException();
					}
					Calendar dolazak = Calendar.getInstance();
					dolazak.set(Calendar.HOUR_OF_DAY, dolazakHInt);
					dolazak.set(Calendar.MINUTE, dolazakMInt);
					dolazak.set(Calendar.SECOND, 0);
					s.setDolazak(dolazak.getTime());
				}
				stanice.add(s);
				destinacije.add(d);
			}
			rutaJPARepo.saveAndFlush(r);
			prevoznikJPARepo.saveAndFlush(p);
			tipRuteJPARepo.saveAndFlush(tp);
			stanice.stream().forEach(x -> stanicaJPARepo.saveAndFlush(x));
			destinacije.stream().forEach(x -> destinacijaJPARepo.saveAndFlush(x));
			redirectAttributes.addFlashAttribute("message", "Ruta je uspesno dodata.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Nisu dobro uneti podaci, probajte ponovo.");
		}
		return "redirect:/admin/initUnosRute";
	}
	
	@RequestMapping(value="danasnjaZarada", method=RequestMethod.GET)
	public String danasnjaZarada(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Korisnik user = (Korisnik)request.getSession().getAttribute("user");
		if(user==null) {
			return "redirect:/korisnik/loginPage";
		}else if(!user.getUlogakorisnka().getNazivUloge().equals("RADNIK")) {
			redirectAttributes.addFlashAttribute("autherr", "Ova stranica je za radike");
			return "redirect:/korisnik/initPocetna";
		}
		List<Karta> prodateKarte = kartaJPARepo.prodajeRadnikaZaDanas(user.getKorisnikID());
		m.addAttribute("brojKarata", prodateKarte.size());
		double ukupnaZarada = prodateKarte.stream().mapToDouble(x -> x.getKonacnaCena()).sum();
		m.addAttribute("ukupnaZarada", ukupnaZarada);
		return "danasnjaZarada";
	}
	
}
