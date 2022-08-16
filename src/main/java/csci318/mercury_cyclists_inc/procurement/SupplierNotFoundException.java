package csci318.mercury_cyclists_inc.procurement;

public class SupplierNotFoundException extends RuntimeException {
    SupplierNotFoundException(Long id) {
        super("Could not find supplier id: " + id);
    }    
}
