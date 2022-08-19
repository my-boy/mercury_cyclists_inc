package csci318.mercury_cyclists_inc.procurement.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(Long id) {
        super("Could not find contact id: " + id);
    }    
}
