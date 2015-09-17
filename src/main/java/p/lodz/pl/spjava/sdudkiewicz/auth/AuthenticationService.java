package p.lodz.pl.spjava.sdudkiewicz.auth;

public interface AuthenticationService {

	boolean authenticate(String username, String password) throws Exception;

}