package p.lodz.pl.spjava.sdudkiewicz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import p.lodz.pl.spjava.sdudkiewicz.models.Customer;
import p.lodz.pl.spjava.sdudkiewicz.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByCn(String cn);
    List<User> findByUid(String uid);
}
