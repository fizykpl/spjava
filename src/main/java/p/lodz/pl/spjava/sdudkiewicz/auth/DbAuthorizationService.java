package p.lodz.pl.spjava.sdudkiewicz.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p.lodz.pl.spjava.sdudkiewicz.models.Admin;
import p.lodz.pl.spjava.sdudkiewicz.repository.AdminRepository;

@Service
public class DbAuthorizationService implements AuthorizationService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<String> getRolesByUid(String uid) {
		Admin findByUid = adminRepository.findByUid(uid);
		if (findByUid != null) {
			return Arrays.asList("ADMIN");
		} else {
			return Collections.emptyList();
		}
	}
}
