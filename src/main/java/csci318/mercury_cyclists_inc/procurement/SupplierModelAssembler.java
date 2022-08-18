package csci318.mercury_cyclists_inc.procurement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class SupplierModelAssembler implements RepresentationModelAssembler<Supplier,EntityModel<Supplier>> {

    @Override
    public EntityModel<Supplier> toModel(Supplier supplier) {
        return EntityModel.of(supplier,
            linkTo(methodOn(ContactController.class).one(supplier.getId())).withSelfRel(),
            linkTo(methodOn(ContactController.class).all()).withRel("suppliers"));
    }
}
