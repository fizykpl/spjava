package p.lodz.pl.spjava.sdudkiewicz.models;

import java.util.List;
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
//    @JoinTable(name = "mtom",
//            joinColumns = @JoinColumn(name = "domain_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Domain> domains;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

    public List<Domain> getDomains() {
        return domains;
    }
    
    
  
  public User() { }

  public User(long id) { 
    this.id = id;
  }

  public User(String cn, String uid) {
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


  
} // class User
