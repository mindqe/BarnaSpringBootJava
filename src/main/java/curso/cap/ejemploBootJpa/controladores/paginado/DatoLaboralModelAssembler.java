package curso.cap.ejemploBootJpa.controladores.paginado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.controladores.rest.DatoLaboralRestController;
import curso.cap.ejemploBootJpa.entidades.DatoLaboral;

@Component
public class DatoLaboralModelAssembler implements SimpleRepresentationModelAssembler<DatoLaboral>{

	@Override
	public void addLinks(EntityModel<DatoLaboral> resource) {
		resource.add(linkTo(methodOn(DatoLaboralRestController.class)
				.datoLaboralById(resource.getContent().getId())).withSelfRel());
		resource.add(linkTo(methodOn(CargoRestController.class)
				.cargosById(resource.getContent().getCargo().getId())).withRel("cargo"));
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<DatoLaboral>> resources) {
		for (EntityModel<DatoLaboral> datoLaboral : resources) {
			datoLaboral.add(linkTo(methodOn(DatoLaboralRestController.class)
					.datoLaboralById(datoLaboral.getContent().getId())).withSelfRel());
			datoLaboral.add(linkTo(methodOn(CargoRestController.class)
					.cargosById(datoLaboral.getContent().getCargo().getId())).withRel("cargo"));
		}
	}

}
 