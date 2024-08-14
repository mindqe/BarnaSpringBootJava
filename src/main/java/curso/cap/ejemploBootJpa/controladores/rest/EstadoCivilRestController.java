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

import curso.cap.ejemploBootJpa.controladores.paginado.EstadoCivilModelAssembler;
import curso.cap.ejemploBootJpa.entidades.EstadoCivil;
import curso.cap.ejemploBootJpa.repositorios.EstadoCivilCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EstadoCivilRestController
{
	@Autowired
	private EstadoCivilCRUDRepository estadoCivilCRUDRepository;
	
	@Autowired
	private PagedResourcesAssembler<EstadoCivil> pagedResourcesAssembler;
	
	@Autowired
	private EstadoCivilModelAssembler estadoCivilModelAssembler;


	@GetMapping("/estadosCiviles")
	public CollectionModel<EstadoCivil> estadosCiviles()
	{
		Iterable<EstadoCivil> estadosCiviles = getEstadoCivilCRUDRepository().findAll();
		for (EstadoCivil estadoCivil : estadosCiviles)
		{
			estadoCivil.add(linkTo(methodOn(EstadoCivilRestController.class).estadosCivilesById(estadoCivil.getId())).withSelfRel());
		}

		return CollectionModel.of(estadosCiviles);
	}
	

	@GetMapping("/estadosCiviles/{id}")
	public EntityModel<EstadoCivil> estadosCivilesById(@PathVariable int id)
	{
		EstadoCivil estadoCivil = getEstadoCivilCRUDRepository().findById(id).get();
		estadoCivil.add(linkTo(methodOn(EstadoCivilRestController.class).estadosCivilesById(estadoCivil.getId())).withSelfRel());

		return EntityModel.of(estadoCivil);
	}
	
	@GetMapping("/estadosCivilesAntiguo/{cantidad}/{pagina}/{orden}")
	public Iterable<EstadoCivil> getEstadosCivilesPaginadosAndOrdenados(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden)
	{
		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		return getEstadoCivilCRUDRepository().findAll(pageable);
	}
	
	@GetMapping("/estadosCiviles/{cantidad}/{pagina}/{orden}")
	public Page<EstadoCivil> getEstadosCivilesAllPageable(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<EstadoCivil> estadosciviles = getEstadoCivilCRUDRepository().findAll(pageable);
		
		for (EstadoCivil estadoCivil : estadosciviles)
		{
			estadoCivil.add(linkTo(methodOn(EstadoCivilRestController.class).estadosCivilesById(estadoCivil.getId())).withSelfRel());
		}

		return estadosciviles;

	}
	
	
	@GetMapping("/estadosCivilesv2/{cantidad}/{pagina}/{orden}")
	public PagedModel<EntityModel<EstadoCivil>> getEstadosCivilesAllPageablev2(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<EstadoCivil> estadosCiviles = getEstadoCivilCRUDRepository().findAll(pageable);
		
		PagedModel<EntityModel<EstadoCivil>> datosLaboralesPAginados=pagedResourcesAssembler.toModel(estadosCiviles, estadoCivilModelAssembler);
		return datosLaboralesPAginados;

	}
	

	@DeleteMapping("/estadosCiviles/{id}")
	public String deleteEstadosCivilesById(@PathVariable int id)
	{
		try
		{
			getEstadoCivilCRUDRepository().deleteById(id);
			return "OK";
		}catch(EmptyResultDataAccessException e) {
			return "REGISTRO NO EXISTE";
		}
		catch (Exception e)
		{
			return "KO";
		}
	}

	@PostMapping("/estadosCiviles")
	public Optional<EstadoCivil> altaModificaEstadoCivil(@RequestBody EstadoCivil estadoCivil)
	{
		getEstadoCivilCRUDRepository().save(estadoCivil);

		return Optional.ofNullable(estadoCivil);
	}

}
