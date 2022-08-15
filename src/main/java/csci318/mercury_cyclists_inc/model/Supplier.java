package csci318.mercury_cyclists_inc.model;

import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Supplier {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column
    private String companyName;
    private String base;

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public Supplier() {

    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String newCompanyName) {
        this.companyName = newCompanyName;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String newBase) {
        this.base = newBase;
    }

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }

    public void addContact(Contact newContact) {
        this.contacts.add(newContact);
    }

}