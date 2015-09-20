package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapAuthenticationService implements AuthenticationService {

	private DirContext ctx = null;

	public LdapAuthenticationService() throws NamingException {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://studdev.zsk.p.lodz.pl:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,
				"ou=list,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL");
		env.put(Context.SECURITY_CREDENTIALS, "listerine");
		ctx = new InitialDirContext(env);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hello.AuthenticationService#authenticate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean authenticate(String uid, String password) throws Exception {
		SearchControls controls = new SearchControls();
		controls.setReturningObjFlag(true);
		controls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		controls.setCountLimit(1);
		String filter = String.format("(uid=%s)(userPassword=%s)", uid, password);
		NamingEnumeration<SearchResult> search = ctx.search(
				"ou=studenci,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL",
				filter, controls);
		return search.hasMore();
	}

//	public static void main(String[] args) throws Exception {
//		AuthenticationService ldapService = new LdapAuthenticationService();
//		ldapService.authenticate("janko", "haslojanko");
//	}
}
