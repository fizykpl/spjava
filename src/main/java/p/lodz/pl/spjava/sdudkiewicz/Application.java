package p.lodz.pl.spjava.sdudkiewicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import p.lodz.pl.spjava.sdudkiewicz.fakeDB.Domain;
import p.lodz.pl.spjava.sdudkiewicz.fakeDB.Domains;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        Domains.add( new Domain("spjava", "07", true, "bob"));
    }

}
