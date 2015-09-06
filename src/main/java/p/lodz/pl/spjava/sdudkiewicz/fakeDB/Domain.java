/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.fakeDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.thymeleaf.util.ArrayUtils;

/**
 *
 * @author sylwekabc06
 */
public class Domain {

    private Subject subject;
    private boolean active;
    private String printUsers;
    public Set<String> users = new HashSet<String>();

    ;



    public Domain() {
    }

    public Domain(String name, String number, boolean active, String... users) {
        this.subject = new Subject(name, number);
        this.active = active;
        setUsers(users);
        Domains.add(this);
        
        this.printUsers = Arrays.toString((String[]) ArrayUtils.toArray(users));
    }
    public String getSubject() {
        return subject.getSubject();
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public String getPrintUsers() {
        return printUsers;
    }

    public boolean cheakUser(String name) {
        return users.contains(name);

    }

    public void setUsers(String[] users) {
        for (String user : users) {
            this.users.add(user);

        }
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.subject);
        hash = 97 * hash + (this.active ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domain other = (Domain) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "Domain{" + "subject=" + subject + ", active=" + active + ", users=" + printUsers + '}';
    }

}
