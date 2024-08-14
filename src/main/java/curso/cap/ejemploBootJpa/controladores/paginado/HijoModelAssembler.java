package curso.cap.ejemploBootJpa.controladores.paginado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.controladores.rest.HijoRestController;
import curso.cap.ejemploBootJpa.entidades.Cargo;
import curso.cap.ejemploBootJpa.entidades.Hijo;

@Component
public class HijoModelAssembler implements SimpleRepresentationModelAssembler<Hijo>{

	@Override
	public void addLinks(EntityModel<Hijo> resource) {
		
		resource.add(linkTo(methodOn(HijoRestController.class).hijosById(resource.getContent().getId())).withSelfRel());

		
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Hijo>> resources) {
		
		for (EntityModel<Hijo> hijoEntityModel : resources)
		{
			hijoEntityModel.add(linkTo(methodOn(HijoRestController.class).hijosById(hijoEntityModel.getContent().getId())).withSelfRel());

		}

		
	}
	

}
