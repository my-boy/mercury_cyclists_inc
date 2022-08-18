package csci318.mercury_cyclists_inc.procurement;

import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SupplierController {
    
    private final SupplierRepository supplierRepository;
    private final ContactRepository contactRepository;

    private final SupplierModelAssembler assembler;


    SupplierController(SupplierRepository supplierRepository, ContactRepository contactRepository, SupplierModelAssembler assembler) {
        this.supplierRepository = supplierRepository;
        this.contactRepository = contactRepository;
        this.assembler = assembler;
    }

    // REST GET
    // All
    @GetMapping("/suppliers")
    CollectionModel<EntityModel<Supplier>> all() {
        List<EntityModel<Supplier>> suppliers = supplierRepository.findAll().stream()
            .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(suppliers,
            linkTo(methodOn(SupplierController.class).all()).withSelfRel());
    }
    // One
    @GetMapping("/suppliers/{id}")
    EntityModel<Supplier> one(@PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));

        return assembler.toModel(supplier);
    }

    // REST POST
    @PostMapping("/suppliers")
    ResponseEntity<?> newSupplier(@RequestBody Supplier newSupplier) {
        EntityModel<Supplier> entityModel = assembler.toModel(supplierRepository.save(newSupplier));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // REST PUT
    @PutMapping("/suppliers/{id}")
    ResponseEntity<?> replaceSupplier(@RequestBody Supplier newSupplier, @PathVariable Long id) {
        Supplier updatedSupplier = supplierRepository.findById(id).map(supplier -> {
            supplier.setCompanyName(newSupplier.getCompanyName());
            supplier.setBase(newSupplier.getBase());
            supplier.setContacts(newSupplier.getContacts());
            return supplierRepository.save(supplier);
        }).orElseGet(() -> {
            newSupplier.setId(id);
            return supplierRepository.save(newSupplier);
        });

        EntityModel<Supplier> entityModel = assembler.toModel(updatedSupplier);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    // Adding a contact
    @PutMapping("/suppliers/{id}/contacts/{contactId}")
    ResponseEntity<?> addContact(@PathVariable Long id, @PathVariable Long contactId) {
        Supplier updatedSupplier = supplierRepository.findById(id).map(supplier -> {
            Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException(id));
            supplier.addContact(contact);
            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new SupplierNotFoundException(id));

        EntityModel<Supplier> entityModel = assembler.toModel(updatedSupplier);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }


    // REST DELETE
    @DeleteMapping("/suppliers/{id}")
    ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        supplierRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}