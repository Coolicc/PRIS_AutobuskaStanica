//package autobuskaStanica.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import autobuskaStanica.service.RolesService;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//    private RolesService rolesService;
//	
//	@Autowired
//    private UserDetailsService userDetailsService;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(rolesService);
//			    auth.inMemoryAuthentication()
//	      .withUser("admin").password("admin")
//	      .roles("ADMIN")
//	      .and()
//	      .withUser("radnik").password("radnik")
//	      .roles("RADNIK");
//	}
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/korisnik/registracija", "/korisnik/registracijaPage").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
////                .antMatchers("/korisnik/**").hasRole("USER")
//                .antMatchers("/korisnik/initPocetna").authenticated()
////                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/korisnik/loginPage").loginProcessingUrl("/korisnik/login").defaultSuccessUrl("/korisnik/initPocetna")
//                .usernameParameter("username").passwordParameter("password")
//                .permitAll()
//                .and()
//            .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/korisnik/odava"))
//            .permitAll();
//    }
//	
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth)
////	  throws Exception {
////	    auth.authenticationProvider(authenticationProvider());
////	}
////	 
////	@Bean
////	public DaoAuthenticationProvider authenticationProvider() {
////	    DaoAuthenticationProvider authProvider
////	      = new DaoAuthenticationProvider();
////	    authProvider.setUserDetailsService(userDetailsService);
////	    return authProvider;
////	}
//}
