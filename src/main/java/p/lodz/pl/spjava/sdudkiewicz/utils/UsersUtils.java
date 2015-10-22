package p.lodz.pl.spjava.sdudkiewicz.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;

import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.AdminRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;

public class UsersUtils {
	

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	static
	AdminRepository adminRepository; 
	
	@Autowired
	static
	DomainRepository domainRepository; 
	
	private static final Logger LOGGER = Logger.getLogger(UsersUtils.class
			.getName());

	public static List<User> getUsers() {
		

		
		List<User> users = new ArrayList<User>();
		Hashtable<String,String> env = getLdapProperties();
		
		DirContext ctx = null;
		NamingEnumeration results = null;
		try {
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setReturningObjFlag(true);
			controls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
			results = ctx.search(env.get("NAME_SEARCH"),env.get("FILTER"), controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();

				Attribute attr = attributes.get("cn");
				String cn = (String) attr.get();
				Attribute attr1 = attributes.get("uid");
				String uid = (String) attr1.get();

				User u = new User(uid, cn);
				users.add(u);

				LOGGER.info("Found User " + u.toString());
			}
		} catch (NamingException e) {
			LOGGER.warning(e.getMessage());
			throw new RuntimeException(e);

		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
				}
			}
		}
		return users;

	}

	public static Hashtable<String, String> getLdapProperties() {
		
		
		ResourceBundle rb = ResourceBundle.getBundle("ldap");
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, rb.getString("PROVIDER_URL"));
		env.put(Context.SECURITY_AUTHENTICATION, rb.getString("SECURITY_AUTHENTICATION"));
		env.put(Context.SECURITY_PRINCIPAL, rb.getString("SECURITY_PRINCIPAL"));
		env.put(Context.SECURITY_CREDENTIALS, rb.getString("SECURITY_CREDENTIALS"));
		
		env.put("NAME_SEARCH", rb.getString("NAME_SEARCH"));
		env.put("FILTER", rb.getString("FILTER"));
		return env;
	}
        

}
