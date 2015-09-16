package p.lodz.pl.spjava.sdudkiewicz;

import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

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
            auth
                    .ldapAuthentication()
                    .userSearchBase("ou=studenci,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL")
                    .userSearchFilter("uid={0}")
                    .contextSource()
                           .url("ldap://studdev.zsk.p.lodz.pl:389")
//                    .managerDn("list")
                    .managerDn("ou=list,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL")
                    .managerPassword("listerine");
//     
//            
//            auth
//                    .ldapAuthentication()
//                    .userDnPatterns("uid={0},ou=people")
//                    .contextSource()
//                        .ldif("classpath:test-server.ldif");
            
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
