package csci318.mercury_cyclists_inc.procurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import csci318.mercury_cyclists_inc.procurement.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
