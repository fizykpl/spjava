package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.List;

public interface AuthorizationService {

	public List<String> getRolesByUid(String uid);
}
