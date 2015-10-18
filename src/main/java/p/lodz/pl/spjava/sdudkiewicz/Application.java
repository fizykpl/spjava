package p.lodz.pl.spjava.sdudkiewicz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;
import p.lodz.pl.spjava.sdudkiewicz.utils.UsersUtils;

@SpringBootApplication
//public class Application implements CommandLineRunner {
	public class Application extends SpringBootServletInitializer  implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DomainRepository domainRepository;
        

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

            List<User> users = UsersUtils.getUsers();
            List<User> findAll = (List<User>) userRepository.findAll();

            boolean removeAll = users.removeAll(findAll);
            userRepository.save(users);
	}

}
