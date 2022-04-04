package com.hydroyura.TechDocsManager.Config;
/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	

	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("YuryKlimchuk")
				.password("PASSWORD")
				.roles("ADMIN");
		
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors().disable()
			.csrf().disable()
			.authorizeRequests()
				//.antMatchers("/planning/**").hasRole("ADMIN")
				//.antMatchers("/products/list/**").hasAnyRole("ADMIN", "TEHNOLOG")
				.antMatchers("/**").permitAll()
			    .anyRequest().authenticated()
			.and()
				.formLogin();
		
		
		
		/*
		http
			.authorizeRequests()
				.anyRequest()
				.hasRole("ADMIN")
				.anyRequest().authenticated()
			.and().formLogin();
		*/
		
	
//	}


//}
//*/
/*
 
  @Configuration
 @EnableWebSecurity
 public class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        }
 }
 
 */
