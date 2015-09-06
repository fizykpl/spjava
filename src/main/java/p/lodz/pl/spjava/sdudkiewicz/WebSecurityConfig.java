package p.lodz.pl.spjava.sdudkiewicz;

import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.directory.DirContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(WebSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("configure" + http.toString());
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }



    @Configuration
    protected static class AuthenticationConfiguration extends
                GlobalAuthenticationConfigurerAdapter {

        private static final Logger LOGGER = Logger.getLogger(AuthenticationConfiguration.class.getName());

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            
            LOGGER.info("init " + auth.toString());
//            auth
//                    .ldapAuthentication()                                             
//                    .userDnPatterns("uid={0},ou=studenci,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL")
//                    .contextSource()
////                           .root("ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL")
//                           .ldif("classpath:content.ldif").;
//            
            auth
                    .ldapAuthentication()
                    .userDnPatterns("uid={0},ou=people")
                    .contextSource()
//                        .root("dc=google,dc=org")
//                        .root("dc=springframework,dc=org")
                        .ldif("classpath:test-server.ldif");
            
//            DirContext ctx = cre
            
            
            
   
            
            
            
            
//      ldapTemplate.search(  query().where("objectclass").is("person"),  new AttributesMapper<String>() {
//          public String mapFromAttributes(Attributes attrs)
//               throws NamingException {
//               return attrs.get("cn").get().toString();
//            }
//         });

    }
    }
}
