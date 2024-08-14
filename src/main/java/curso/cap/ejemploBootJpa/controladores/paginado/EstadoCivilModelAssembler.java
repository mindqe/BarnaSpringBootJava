package curso.cap.ejemploBootJpa.controladores.paginado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.controladores.rest.EstadoCivilRestController;
import curso.cap.ejemploBootJpa.entidades.Cargo;
import curso.cap.ejemploBootJpa.entidades.EstadoCivil;

@Component
public class EstadoCivilModelAssembler implements SimpleRepresentationModelAssembler<EstadoCivil>{

	@Override
	public void addLinks(EntityModel<EstadoCivil> resource) {
		
		resource.add(linkTo(methodOn(EstadoCivilRestController.class).estadosCivilesById(resource.getContent().getId())).withSelfRel());
		
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<EstadoCivil>> resources) {
		
		for (EntityModel<EstadoCivil> estadoCivilEntityModel : resources)
		{
			estadoCivilEntityModel.add(linkTo(methodOn(EstadoCivilRestController.class).estadosCivilesById(estadoCivilEntityModel.getContent().getId())).withSelfRel());

		}
		
	}

}
