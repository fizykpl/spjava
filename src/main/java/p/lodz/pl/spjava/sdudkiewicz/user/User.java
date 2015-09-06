/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.user;

import java.util.Objects;

/**
 *
 * @author Sylwester
 */
public class User {
    private final String cn;
    private final String uid;

    public User(String cn, String uid) {
        this.cn = cn;
        this.uid = uid;
    }

    public String getCn() {
        return new String(cn);
    }

    public String getUid() {
        return new String(uid);
    }

    @Override
    public String toString() {
        return "User{" + "cn=" + cn + ", uid=" + uid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cn);
        hash = 97 * hash + Objects.hashCode(this.uid);
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
        final User other = (User) obj;
        if (!Objects.equals(this.cn, other.cn)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        return true;
    }
    
    
    
}
