/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.user;

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
    
    
    
}
