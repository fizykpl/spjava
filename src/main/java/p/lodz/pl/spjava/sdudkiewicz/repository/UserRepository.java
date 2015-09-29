package p.lodz.pl.spjava.sdudkiewicz.repository;

import org.springframework.data.repository.CrudRepository;

import p.lodz.pl.spjava.sdudkiewicz.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByCn(String cn);

	User findByUid(String uid);
}
