package csci318.mercury_cyclists_inc.procurement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
    
    private final SupplierRepository supplierRepository;

    SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/Suppliers")
    List<Supplier> findAllSupplier() {
        return supplierRepository.findAll();
    }

    @GetMapping("/Suppliers/{id}")
    Supplier getSupplierById(@PathVariable Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
    }

    @PostMapping("/Suppliers")
    Supplier createSupplier(@RequestBody Supplier newSupplier) {
        return supplierRepository.save(newSupplier);
    }

    // @PutMapping("/Suppliers/{id}")
    // Supplier replaceSupplier(@RequestBody Supplier newSupplier, @PathVariable Long id) {
    //     return supplierRepository.findById(id).map(supplier -> {
    //         supplier.setCompanyName(newSupplier.getCompanyName());
    //         supplier.setBase(newSupplier.getBase());
    //         supplier.setContacts(newSupplier.getContacts());
    //     }).orElseGet(() -> {
    //         newSupplier.setId(id);
    //         return repository.save(newSupplier);
    //     });
    // }

    @DeleteMapping("/Suppliers/{id}")
    void deleteSupplier(@PathVariable Long id) {
        supplierRepository.deleteById(id);
    }
}
