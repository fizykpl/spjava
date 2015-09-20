package p.lodz.pl.spjava.sdudkiewicz.configs;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import p.lodz.pl.spjava.sdudkiewicz.auth.LdapAuthenticationProvider;
import p.lodz.pl.spjava.sdudkiewicz.auth.LdapAuthenticationService;
import p.lodz.pl.spjava.sdudkiewicz.auth.MysqlAuthorizationService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(WebSecurityConfig.class.getName());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("configure" + http.toString());
		http.authorizeRequests().antMatchers("/css/**").permitAll().anyRequest().authenticated().and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(
				new LdapAuthenticationProvider(new LdapAuthenticationService(), new MysqlAuthorizationService()));
	}
}
