package curso.cap.ejemploBootJpa.controladores.paginado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.entidades.Cargo;

@Component
public class CargoModelAssembler implements SimpleRepresentationModelAssembler<Cargo> {

	@Override
	public void addLinks(CollectionModel<EntityModel<Cargo>> resources)
	{
		for (EntityModel<Cargo> cargoEntityModel : resources)
		{
			cargoEntityModel.add(linkTo(methodOn(CargoRestController.class).cargosById(cargoEntityModel.getContent().getId())).withSelfRel());

		}

	}

	@Override
	public void addLinks(EntityModel<Cargo> resource) {
		resource.add(
				linkTo(methodOn(CargoRestController.class).cargosById(resource.getContent().getId())).withSelfRel());

	}
}
