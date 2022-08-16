package csci318.mercury_cyclists_inc.procurement;

public class ContactNotFoundException extends RuntimeException {
    ContactNotFoundException(Long id) {
        super("Could not find contact id: " + id);
    }    
}
