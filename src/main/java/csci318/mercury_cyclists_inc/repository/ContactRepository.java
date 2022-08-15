package csci318.mercury_cyclists_inc.repository;

import csci318.mercury_cyclists_inc.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}