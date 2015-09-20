/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import p.lodz.pl.spjava.sdudkiewicz.models.Admin;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;

/**
 *
 * @author Sylwester
 */
public interface AdminRepository extends CrudRepository<Admin, Long>{
    Admin findByUid(String uid);
}
