package p.lodz.pl.spjava.sdudkiewicz.configs;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import p.lodz.pl.spjava.sdudkiewicz.auth.LdapAuthenticationProvider;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApplicationContext context;

	private static final Logger LOGGER = Logger
			.getLogger(WebSecurityConfig.class.getName());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("configure" + http.toString());
		http.authorizeRequests().antMatchers("/css/**").permitAll()
				.antMatchers("/domains").hasRole("ADMIN")
				.antMatchers("/domain/*").hasRole("ADMIN").anyRequest()
				.authenticated().and().csrf().and().exceptionHandling()
				.accessDeniedPage("/permissionDenied").and().formLogin();
		

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		LdapAuthenticationProvider bean = context.getBean(LdapAuthenticationProvider.class);
		auth.authenticationProvider(bean);
		
	}

}
