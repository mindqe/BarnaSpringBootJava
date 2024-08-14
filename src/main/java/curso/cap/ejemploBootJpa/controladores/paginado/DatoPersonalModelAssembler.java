package curso.cap.ejemploBootJpa.controladores.paginado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import curso.cap.ejemploBootJpa.controladores.rest.DatoPersonalRestController;
import curso.cap.ejemploBootJpa.controladores.rest.EstadoCivilRestController;
import curso.cap.ejemploBootJpa.controladores.rest.HijoRestController;
import curso.cap.ejemploBootJpa.entidades.DatoPersonal;
@Component
public class DatoPersonalModelAssembler implements SimpleRepresentationModelAssembler<DatoPersonal> {

	@Override
	public void addLinks(EntityModel<DatoPersonal> datoPersonal) {
		
		datoPersonal.add(linkTo(methodOn(HijoRestController.class).hijosById(datoPersonal.getContent().getId()))
				.withRel("hijo"));
		datoPersonal.add(linkTo(
				methodOn(EstadoCivilRestController.class).estadosCivilesById(datoPersonal.getContent().getId()))
						.withRel("estadoCivil"));
		datoPersonal.add(
				linkTo(methodOn(DatoPersonalRestController.class).datosPById(datoPersonal.getContent().getId()))
						.withSelfRel());
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<DatoPersonal>> resources) {

		for (EntityModel<DatoPersonal> datoPersonal : resources) {
			datoPersonal.add(linkTo(methodOn(HijoRestController.class).hijosById(datoPersonal.getContent().getId()))
					.withRel("hijo"));
			datoPersonal.add(linkTo(
					methodOn(EstadoCivilRestController.class).estadosCivilesById(datoPersonal.getContent().getId()))
							.withRel("estadoCivil"));
			datoPersonal.add(
					linkTo(methodOn(DatoPersonalRestController.class).datosPById(datoPersonal.getContent().getId()))
							.withSelfRel());
		}

	}

}
