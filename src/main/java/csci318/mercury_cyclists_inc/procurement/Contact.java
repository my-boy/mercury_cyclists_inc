package csci318.mercury_cyclists_inc.procurement;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private long id;
    
    

    @Column
    private String name;
    private String phone;
    private String email;
    private String position;

    @ManyToOne
    @JoinColumn(name = "contact_name")
    @JsonIgnore
    // hide contact name in supplier names list
    private Supplier supplier;

    // constructors
    public Contact() {
        name = "";
        phone = "";
        email = "";
        position = "";
    }
    public Contact(String name, String phone, String email, String position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String newEmail) {
        this.name = newEmail;
    }

    public String getPosition() {
        return this.position;
    }
    public void setPosition(String newPosition) {
        this.position = newPosition;
    }
}
