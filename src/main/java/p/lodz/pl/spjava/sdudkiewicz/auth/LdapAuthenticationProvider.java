package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

@Service
public class LdapAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private final AuthenticationService authService;
	@Autowired
	private final AuthorizationService authorizationService;

	@Autowired
	public LdapAuthenticationProvider(AuthenticationService authService,
			AuthorizationService authorizationService) {
		super();
		this.authService = authService;
		this.authorizationService = authorizationService;
	}

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		String password = (String) auth.getCredentials();
		String username = (String) auth.getPrincipal();
		try {
			boolean authenticated = authService
					.authenticate(username, password);
			if (authenticated) {
				List<String> rolesByUid = authorizationService
						.getRolesByUid(username);
				List<SimpleGrantedAuthority> grantendAuthorities = rolesByUid
						.stream()
						.map(r -> new SimpleGrantedAuthority("ROLE_" + r))
						.collect(Collectors.toList());
				return new UsernamePasswordAuthenticationToken(
						auth.getPrincipal(), auth.getCredentials(),
						grantendAuthorities);
			} else {
				throw new BadCredentialsException(
						"Incorrect username or password");
			}
		} catch (Exception e) {
			throw new AuthenticationServiceException("Cannot authenticate", e);
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return ClassUtils.isAssignable(
				UsernamePasswordAuthenticationToken.class, arg0);
	}

}
