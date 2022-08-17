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
public class ContactController {

    private final ContactRepository contactRepository;

    private final ContactModelAssembler assembler;

    ContactController(ContactRepository contactRepository, ContactModelAssembler assembler) {
        this.contactRepository = contactRepository;
        this.assembler = assembler;
    }

    // REST GET
    // All
    @GetMapping("/contacts")
    CollectionModel<EntityModel<Contact>> all() {
        List<EntityModel<Contact>> contacts = contactRepository.findAll().stream()
            .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(contacts,
            linkTo(methodOn(ContactController.class).all()).withSelfRel());
    }
    // One
    @GetMapping("/contacts/{id}")
    EntityModel<Contact> one(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));

        return assembler.toModel(contact);
    }

    // REST POST
    @PostMapping("/contacts")
    ResponseEntity<?> newContact(@RequestBody Contact newContact) {
        EntityModel<Contact> entityModel = assembler.toModel(contactRepository.save(newContact));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // REST PUT
    @PutMapping("/contacts/{id}")
    ResponseEntity<?> replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
        Contact updatedContact = contactRepository.findById(id).map(contact -> {
            contact.setFirstName(newContact.getFirstName());
            contact.setLastName(newContact.getFirstName());
            contact.setPhone(newContact.getPhone());
            contact.setEmail(newContact.getEmail());
            contact.setPosition(newContact.getPosition());
            return contactRepository.save(contact);
        }).orElseGet(() -> {
            newContact.setId(id);
            return contactRepository.save(newContact);
        });

        EntityModel<Contact> entityModel = assembler.toModel(updatedContact);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // REST DELETE
    @DeleteMapping
    ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}