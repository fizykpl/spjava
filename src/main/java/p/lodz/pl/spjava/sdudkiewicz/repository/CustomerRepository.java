package p.lodz.pl.spjava.sdudkiewicz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import p.lodz.pl.spjava.sdudkiewicz.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
