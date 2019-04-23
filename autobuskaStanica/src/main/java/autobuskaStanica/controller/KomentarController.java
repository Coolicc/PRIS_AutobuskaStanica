package autobuskaStanica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import autobuskaStanica.model.Komentar;
import autobuskaStanica.model.Prevoznik;
import autobuskaStanica.repository.KomentarJPARepo;
import autobuskaStanica.repository.PrevoznikJPARepo;

@Controller
@RequestMapping(value="/komentar")
public class KomentarController {
	
	@Autowired
	KomentarJPARepo kjr;
	
	@RequestMapping(value="komentari", method=RequestMethod.GET)
	public String prevoznici(HttpServletRequest request, Model m) {
		List<Komentar> komentari = kjr.vratiKomentareZaPrevoznika(Integer.parseInt(request.getParameter("prevoznikID")));
		m.addAttribute("komentari", komentari);
		return "komentari";
	}
}
