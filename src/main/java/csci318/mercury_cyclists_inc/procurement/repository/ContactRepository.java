package csci318.mercury_cyclists_inc.procurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import csci318.mercury_cyclists_inc.procurement.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}