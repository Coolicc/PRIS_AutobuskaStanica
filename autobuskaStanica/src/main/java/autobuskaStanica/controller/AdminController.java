package autobuskaStanica.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import autobuskaStanica.repository.DestinacijaJPARepo;
import autobuskaStanica.repository.PrevoznikJPARepo;
import autobuskaStanica.repository.RutaJPARepo;
import autobuskaStanica.repository.StanicaJPARepo;
import autobuskaStanica.repository.TipRuteJPARepo;
import autobuskaStanica.model.Destinacija;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.model.Ruta;
import autobuskaStanica.model.Stanica;
import autobuskaStanica.model.Tippolaska;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

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
	
	@RequestMapping(value="initUnosRute", method=RequestMethod.GET)
	public String initUnosRute(Model m, HttpServletRequest request) {
		List<Tippolaska> tipoviRute = tipRuteJPARepo.findAll();
		List<Prevoznik> prevoznici = prevoznikJPARepo.findAll();
		m.addAttribute("tipoviRute", tipoviRute);
		m.addAttribute("prevoznici", prevoznici);
		m.addAttribute("message", request.getAttribute("message"));
		return "unosRute";
	}
	
	@RequestMapping(value="unosRute", method=RequestMethod.POST)
	public String unosRute(Model m, HttpServletRequest request) {
		List<Destinacija> destinacije = destinacijaJPARepo.findAll();
		System.out.println(destinacije.size());
		Integer brojMesta = 0;
		Integer brojStanica = 0;
		try {
			brojMesta = Integer.parseInt(request.getParameter("brojMesta"));
			brojStanica = Integer.parseInt(request.getParameter("brojStanica"));
		} catch (NumberFormatException e) {
			request.setAttribute("message", "Broj mesta i broj stanica moraju da budu broj.");
			return "forward:/admin/initUnosRute";
		}
		m.addAttribute("tipRute", request.getParameter("tipRute"));
		m.addAttribute("prevoznik", request.getParameter("prevoznik"));
		m.addAttribute("brojMesta", brojMesta);
		m.addAttribute("brojStanica", brojStanica);
		m.addAttribute("destinacije", destinacije);
		return "unosStanica";
	}
	
	@RequestMapping(value="unosStanica", method=RequestMethod.POST)
	public String unosStanica(Model m, HttpServletRequest request) {
		try {
			Ruta r = new Ruta();
			r.setBrMesta(Integer.parseInt(request.getParameter("brojMesta")));
			Prevoznik p = prevoznikJPARepo.findById(Integer.parseInt(request.getParameter("prevoznik"))).get();
			Tippolaska tp = tipRuteJPARepo.findById(Integer.parseInt(request.getParameter("tipRute"))).get();
			r.setPrevoznikBean(p);
			r.setTippolaska(tp);
			r.setDatum(new Date());
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
					s.setDolazak(dolazak.getTime());
				}
			}
			rutaJPARepo.saveAndFlush(r);
			prevoznikJPARepo.saveAndFlush(p);
			tipRuteJPARepo.saveAndFlush(tp);
			stanice.stream().forEach(x -> stanicaJPARepo.saveAndFlush(x));
//			stanicaJPARepo.saveAll(stanice);
			destinacije.stream().forEach(x -> destinacijaJPARepo.saveAndFlush(x));
//			destinacijaJPARepo.saveAll(destinacije);
			request.setAttribute("message", "Ruta je uspesno dodata.");
			System.out.println("asdsadasdasdsadsad");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Nisu dobro uneti podaci, probajte ponovo.");
		}
		return "forward:/admin/initUnosRute";
	}
}
