package csci318.mercury_cyclists_inc.procurement.domain;

import java.util.Objects;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Entity
public class Supplier {
    
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String companyName;
    @Column(name = "base")
    private String base;

    @OneToMany
    private List<Contact> contacts = Collections.<Contact>emptyList();

    // constructors
    public Supplier() {
        companyName = "";
        base = "";
    }
    public Supplier(String companyName, String base, List<Contact> contacts) {
        this.companyName = companyName;
        this.base = base;
        this.contacts = contacts;
    }

    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBase() {
        return this.base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact newContact) {
        this.contacts.add(newContact);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if (!(o instanceof Supplier)) {
            return false;
        }
        Supplier supplier = (Supplier) o;
        return Objects.equals(this.id, supplier.id) && Objects.equals(this.companyName, supplier.companyName) &&
            Objects.equals(this.base, supplier.base) && Objects.equals(this.contacts, supplier.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.companyName, this.base, this.contacts);
    }

    @Override
    public String toString() {
        String retString = "Supplier { id = " + this.id + ", name = " + this.companyName + ", base = " + this.base + ", contacts:\n";
        for (Contact contact : contacts) {
            retString += "id = " + contact.getId() + ", name = " + contact.getName();
        }

        return retString;
    }
}