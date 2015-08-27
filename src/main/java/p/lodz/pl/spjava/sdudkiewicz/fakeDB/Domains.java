/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.fakeDB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.util.ArrayUtils;

/**
 *
 * @author sylwekabc06
 */
public class Domains {

    public static HashSet<Domain> domains = new HashSet<Domain>();

    public static String[] getSubject(String user) {
        List list = new ArrayList<String>();

        for (Domain domain : domains) {
            if (domain.cheakUser(user)) {
                list.add(domain.name+domain.number);
            }
        }

        return (String[]) ArrayUtils.toArray(list);
    }

    public static void add(Domain domain) {
        domains.add(domain);
    }

    public static boolean containsUser(String user) {

        for (Domain domain : domains) {
            for (String u : domain.users) {
                if (u.equals(user)) {
                    return true;
                }

            }
        }
        return false;
    }
}
