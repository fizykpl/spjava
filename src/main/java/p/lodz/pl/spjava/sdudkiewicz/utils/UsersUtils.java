package p.lodz.pl.spjava.sdudkiewicz.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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
import p.lodz.pl.spjava.sdudkiewicz.user.User;

/**
 *
 * @author Sylwester
 */
public class UsersUtils {

    private static final Logger LOGGER = Logger.getLogger(UsersUtils.class.getName());

    public static List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:33389/dc=springframework,dc=org");
        env.put(Context.PROVIDER_URL, "ldap://studdev.zsk.p.lodz.pl:389/ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "ou=list,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL");
        env.put(Context.SECURITY_CREDENTIALS, "listerine");
        DirContext ctx = null;
        NamingEnumeration results = null;
        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("ou=studenci,ou=Wydzial Fizyki Technicznej Informatyki i Matematyki Stosowanej,o=Politechnika Lodzka,c=PL", "objectClass=*", controls);
//            results = ctx.search("", "objectClass=person", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                Attribute attr1 = attributes.get("uid");
                String uid = (String) attr1.get();
                User u = new User(cn, uid);
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
}
