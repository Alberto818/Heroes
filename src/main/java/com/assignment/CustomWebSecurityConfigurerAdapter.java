package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
        auth.inMemoryAuthentication().withUser("auditor").password(passwordEncoder().encode("password")).roles("AUDITOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String realm = "assignment-app";
    	BasicAuthenticationEntryPoint baep = new BasicAuthenticationEntryPoint();
    	baep.setRealmName(realm);
    	
    	http.httpBasic()    
    		.realmName(realm)    		
    		.authenticationEntryPoint(baep)
	    	.and()	    	
	    	.authorizeRequests()	    	
			.antMatchers("/heroes/all")			
	    	.hasAuthority("ROLE_AUDITOR")    		
    		.and().formLogin().defaultSuccessUrl("/swagger-ui.html")
    		.and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
    		.and().csrf().disable();    	      	
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}	