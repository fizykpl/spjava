package p.lodz.pl.spjava.sdudkiewicz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import p.lodz.pl.spjava.sdudkiewicz.models.Customer;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.CustomerRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.DomainRepository;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DomainRepository domainRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //save domain
        if (domainRepository.findBySubject("java01").size() == 0) {
            domainRepository.save(new Domain("java01"));
        }
        //save user
        if (userRepository.findByUid("uid1").size()==0) {
            userRepository.save(new User("Franek", "uid1"));
        }
        if (userRepository.findByUid("uid2").size()==0) {
            userRepository.save(new User("Kimono", "uid2"));
        }

        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = customerRepository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : customerRepository.findByLastName("Bauer")) {
            System.out.println(bauer);
        }
    }

}
