package p.lodz.pl.spjava.sdudkiewicz.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users")
public class User {

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String cn;

	@NotNull
	private String uid;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<Domain> domains;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String uid, String cn) {
		this.cn = cn;
		this.uid = uid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<Domain> getDomains() {
		if (null == domains) {
			return new ArrayList<Domain>();

		}
		{
			return domains;
		}
	}

	public List<String> getDomainsAsString() {

		List<String> out = new ArrayList<String>();
		if (null != domains) {
			for (Domain d : domains) {
				out.add(d.getSubject());
			}
		}
		return out;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.uid);
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

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", cn=" + cn + ", uid=" + uid + '}';
	}

} // class User
