package curso.cap.ejemploBootJpa.controladores.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.cap.ejemploBootJpa.controladores.paginado.DatoPersonalModelAssembler;
import curso.cap.ejemploBootJpa.entidades.DatoPersonal;
import curso.cap.ejemploBootJpa.repositorios.DatosPersonalesCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DatoPersonalRestController {

	@Autowired
	private DatosPersonalesCRUDRepository datoPersonalCRUDepository;

	@Autowired
	private HijoRestController hijoRestController;

	@Autowired
	private EstadoCivilRestController civilRestController;

	@Autowired
	private PagedResourcesAssembler<DatoPersonal> pagedResourcesAssembler;

	@Autowired
	private DatoPersonalModelAssembler datoPersonalModelAssembler;

	@GetMapping("/datosPersonales/{id}")
	public EntityModel<DatoPersonal> datosPById(@PathVariable int id) {
		DatoPersonal datoPersonal = datoPersonalCRUDepository.findById(id).get();

		datoPersonal.add(
				linkTo(methodOn(HijoRestController.class).hijosById(datoPersonal.getHijo().getId())).withRel("hijo"));
		datoPersonal.add(linkTo(
				methodOn(EstadoCivilRestController.class).estadosCivilesById(datoPersonal.getEstadoCivil().getId()))
						.withRel("estadoCivil"));
		datoPersonal
				.add(linkTo(methodOn(DatoPersonalRestController.class).datosPById(datoPersonal.getId())).withSelfRel());

		return EntityModel.of(datoPersonal);
	}

	@GetMapping("/datosPersonales")
	public CollectionModel<DatoPersonal> datosPAll() {
		Iterable<DatoPersonal> datosPersonales = getDatoPersonalCRUDepository().findAll();
		for (DatoPersonal datoPersonal : datosPersonales) {
			datoPersonal.add(linkTo(methodOn(HijoRestController.class).hijosById(datoPersonal.getHijo().getId()))
					.withRel("hijo"));
			datoPersonal.add(linkTo(
					methodOn(EstadoCivilRestController.class).estadosCivilesById(datoPersonal.getEstadoCivil().getId()))
							.withRel("estadoCivil"));
			datoPersonal.add(
					linkTo(methodOn(DatoPersonalRestController.class).datosPById(datoPersonal.getId())).withSelfRel());
		}

		return CollectionModel.of(datosPersonales);
	}

	@DeleteMapping("/datosPersonales/{id}")
	public String deleteDatoPById(@PathVariable int id) {
		try {
			datoPersonalCRUDepository.deleteById(id);
			return "OK";
		} catch (EmptyResultDataAccessException e) {
			return "REGISTRO NO EXISTE";
		} catch (Exception e) {
			return "KO";
		}
	}

	@GetMapping("/datosPersonalesv1/{cantidad}/{pagina}/{orden}")
	public Page<DatoPersonal> getCargosAllPageablev1(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden) {

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<DatoPersonal> datosPersonales = getDatoPersonalCRUDepository().findAll(pageable);
		for (DatoPersonal datoPersonal : datosPersonales) {

			datoPersonal.add(linkTo(methodOn(HijoRestController.class).hijosById(datoPersonal.getHijo().getId()))
					.withRel("hijo"));
			datoPersonal.add(linkTo(
					methodOn(EstadoCivilRestController.class).estadosCivilesById(datoPersonal.getEstadoCivil().getId()))
							.withRel("estadoCivil"));
			datoPersonal.add(
					linkTo(methodOn(DatoPersonalRestController.class).datosPById(datoPersonal.getId())).withSelfRel());
		}

		return datosPersonales;

	}

	@GetMapping("/datosPersonalesv2/{cantidad}/{pagina}/{orden}")
	public PagedModel<EntityModel<DatoPersonal>> getCargosAllPageablev2(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden) {

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<DatoPersonal> datosPersonales = getDatoPersonalCRUDepository().findAll(pageable);

		PagedModel<EntityModel<DatoPersonal>> datosPersonalesPaginados = pagedResourcesAssembler
				.toModel(datosPersonales, datoPersonalModelAssembler);

		return datosPersonalesPaginados;

	}

	@PostMapping("/datoPersonal")
	public Optional<DatoPersonal> altaModificaDatoPersonal(@RequestBody DatoPersonal dPersonal) {

		getDatoPersonalCRUDepository().save(dPersonal);

		return Optional.ofNullable(dPersonal);
	}
}
