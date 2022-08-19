package csci318.mercury_cyclists_inc.procurement.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id) {
        super("Could not find supplier id: " + id);
    }    
}
