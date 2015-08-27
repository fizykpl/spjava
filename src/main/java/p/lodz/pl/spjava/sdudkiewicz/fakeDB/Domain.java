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

/**
 *
 * @author sylwekabc06
 */
public class Domain {

    public String name;
    public String number;
    private boolean active;
    public Set<String> users = new HashSet<String>();;



    public Domain() {
    }

    public Domain(String name, String number, boolean active, String... users) {
        this.name = name;
        this.number = number;
        this.active = active;
        setUsers(users);
        Domains.add(this);

    }

    public boolean cheakUser(String name){
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
        String domain = this.name + this.number;
        hash = 31 * hash + Objects.hashCode(domain);
        hash = 31 * hash + (this.active ? 1 : 0);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String[] array = users.toArray(new String[users.size()]);
        return "Domain{" + "name=" + name + ", number=" + number + ", active=" + active + ", users=" + Arrays.toString(array) + '}';
    }

}
