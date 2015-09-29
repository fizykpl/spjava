/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sylwester
 */
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String uid;

	public Admin() {
	}

	public Admin(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", uid=" + uid + "]";
	}
}
