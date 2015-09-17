package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.Collections;
import java.util.List;

public class MysqlAuthorizationService implements AuthorizationService {

	// implementation
	@Override
	public List<String> getRolesByUid(String uid) {
		return Collections.emptyList();
	}

}
