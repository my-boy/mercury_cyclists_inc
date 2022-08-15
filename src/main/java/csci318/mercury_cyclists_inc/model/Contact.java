package csci318.mercury_cyclists_inc.model;

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

    public Contact() {

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
