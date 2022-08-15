package csci318.mercury_cyclists_inc.controller;

import csci318.mercury_cyclists_inc.repository.ContactRepository;
import csci318.mercury_cyclists_inc.model.Contact;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/Contacts")
    List<Contact> findAllContact() {
        return contactRepository.findAll();
    }

    @GetMapping("/Contacts/{id}")
    Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/Contacts")
    Contact createContact(@RequestBody Contact newContact) {
        return contactRepository.save(newContact);
    }

}
