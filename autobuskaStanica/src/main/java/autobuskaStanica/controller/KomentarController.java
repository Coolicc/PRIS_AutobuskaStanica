package autobuskaStanica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import autobuskaStanica.model.Destinacija;
import autobuskaStanica.model.Komentar;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.model.Tippolaska;
import autobuskaStanica.repository.KomentarJPARepo;
import autobuskaStanica.repository.KomentarRepo;
import autobuskaStanica.repository.PrevoznikJPARepo;

@Controller
@RequestMapping(value="/komentar")
public class KomentarController {
	
	@Autowired
	KomentarJPARepo kjr;
	
	@Autowired
	PrevoznikJPARepo prevoznikJPARepo;
	
	@Autowired
	KomentarRepo kr;
	
	/*@RequestMapping(value="komentari", method=RequestMethod.GET)
	public String getKomentari(HttpServletRequest request, Model m) {
		//List<Komentar> komentari = kjr.vratiKomentareZaPrevoznika(Integer.parseInt(request.getParameter("prevoznikID")));
		List<Komentar> komentari = kjr.findAll();
		m.addAttribute("komentari", komentari);
		return "komentari";
	}*/
	
	/*@RequestMapping(value="initUnosKomentara", method=RequestMethod.GET)
	public String initUnosKomentara(Model m, HttpServletRequest request) {
		List<Prevoznik> prevoznici = prevoznikJPARepo.findAll();
		m.addAttribute("prevoznici", prevoznici);
		request.setAttribute("message", m.asMap().get("message"));
		return "komentari";
	}
	
	@RequestMapping(value="unosKomentara", method=RequestMethod.POST)
	public String unosKomentara(Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Prevoznik p = prevoznikJPARepo.findById(Integer.parseInt(request.getParameter("prevoznik"))).get();
		m.addAttribute("komentar", request.getParameter("komentar"));
		return "komentari";
	}*/
	
	/*@RequestMapping(value="getPrevoznici", method=RequestMethod.GET)
	public String getPrevoznici(Model m, HttpServletRequest request){
		List<Prevoznik> prevoznici=prevoznikJPARepo.findAll();
		m.addAttribute("prevoznici", prevoznici);
		request.getSession().setAttribute("prevoznici", prevoznici);
		return "komentari";
	}
	
	@RequestMapping(value="saveKomentar", method=RequestMethod.POST)
	public String saveKomentar(Model m, @ModelAttribute("komentar") Komentar komentar){		
		kr.saveKomentar(komentar);
		m.addAttribute("komentar", komentar);
		return "komentari";
	}*/

}
