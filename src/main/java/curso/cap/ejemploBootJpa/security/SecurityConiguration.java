package curso.cap.ejemploBootJpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import curso.cap.ejemploBootJpa.services.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityConiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService service;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/user").permitAll();
		//http.authorizeRequests().antMatchers("/nologin").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilterAfter(new JJWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		/*
		 * http.formLogin().loginPage("/login");
		 * http.formLogin().loginProcessingUrl("/autenticar");
		 * http.formLogin().defaultSuccessUrl("/segura");
		 * http.formLogin().failureUrl("/nologin");
		 * http.formLogin().usernameParameter("usuario");
		 * http.formLogin().passwordParameter("clave");
		 * http.logout().logoutSuccessUrl("/login?logout=true"); http.cors().disable();
		 * http.csrf().disable();
		 */
		http.cors();
		http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(getService()).passwordEncoder(new BCryptPasswordEncoder(4));
	}
	
}
