package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.stereotype.Service;

import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

@Service
public class LdapAuthenticationService implements AuthenticationService {

	private DirContext ctx = null;

	public LdapAuthenticationService() throws NamingException {
		Hashtable<String, String> env = UsersUtils.getLdapProperties();
		ctx = new InitialDirContext(env);
	}

	@Override
	public boolean authenticate(String uid, String password) throws Exception {
		SearchControls controls = new SearchControls();
		controls.setReturningObjFlag(true);
		controls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		controls.setCountLimit(11);
		String filter = String.format("(uid=%s)", uid);
		String dn = "ou=studenci,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL";
		NamingEnumeration<SearchResult> search = ctx
				.search(dn,	filter, controls);
		SearchResult next = search.next();		
		
		String name = next.getName()+"," +dn;
		Hashtable<String, String> env =  UsersUtils.getUserProperties(name, password);
		
		// Create the initial context		
		DirContext ctx = new InitialDirContext(env);
		ctx.close();
		return ctx != null;

	}
}
