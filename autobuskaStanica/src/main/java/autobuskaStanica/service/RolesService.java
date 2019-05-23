//package autobuskaStanica.service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import autobuskaStanica.model.Korisnik;
//import autobuskaStanica.model.Ulogakorisnka;
//import autobuskaStanica.repository.KorisnikJPARepo;
//import autobuskaStanica.repository.UlogaKorisnikaJPARepo;
//@Service
//public class RolesService implements UserDetailsService{
//
//	@Autowired
//	KorisnikJPARepo korisnikJpaRepo;
//	@Autowired
//	UlogaKorisnikaJPARepo ulogaJpaRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Korisnik k = korisnikJpaRepo.findByUsername(username);
//        if (k == null) 
//        	throw new UsernameNotFoundException(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Ulogakorisnka uloga = k.getUlogakorisnka();
//        grantedAuthorities.add(new SimpleGrantedAuthority(uloga.getNazivUloge()));
//            
//        return new org.springframework.security.core.userdetails.User(k.getUsername(), k.getPassword(), grantedAuthorities);
//    }
//}
//
