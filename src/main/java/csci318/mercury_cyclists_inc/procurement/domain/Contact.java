package csci318.mercury_cyclists_inc.procurement.domain;

import java.util.Objects;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "supplierId")
    private Long supplierId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "contact_name")
    @JsonIgnore
    // hide contact name in supplier names list
    private Supplier supplier;

    // constructors
    public Contact() {
        firstName = "";
        lastName = "";
        supplierId = (long) -1;
        phone = "";
        email = "";
        position = "";
    }
    public Contact(String firstName, String lastName, Long supplierId, String phone, String email, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.supplierId = supplierId;
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

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(this.id, contact.id) && Objects.equals(this.firstName, contact.firstName) &&
            Objects.equals(this.lastName, contact.lastName) && Objects.equals(this.phone, contact.phone) &&
            Objects.equals(this.email, contact.email) && Objects.equals(this.position, contact.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.phone, this.email, this.position);
    }

    @Override
    public String toString() {
        return "Contact { id = " + this.id + ", name = " + this.firstName + " " + this.lastName + ", phone = " + 
            this.phone + ", email = " + this.email + ", position = " + this.position;
    }
}
