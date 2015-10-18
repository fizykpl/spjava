/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.pl.spjava.sdudkiewicz.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Column(unique = true)
	private String subject;

	private Boolean busy;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mtom", joinColumns = @JoinColumn(name = "domain_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	public Domain() {
	}

	public Domain(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public Set<User> getUsers() {
		return users;
	}

	public Boolean getBusy() {
		return busy;
	}

	public void setBusy(Boolean busy) {
		this.busy = busy;
	}

	@Override
	public String toString() {
		return "Domain{" + "id=" + id + ", subject=" + subject + '}';
	}

}
