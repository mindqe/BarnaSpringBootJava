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

import curso.cap.ejemploBootJpa.controladores.paginado.CargoModelAssembler;
import curso.cap.ejemploBootJpa.entidades.Cargo;
import curso.cap.ejemploBootJpa.repositorios.CargoCRUDRepository;
import curso.cap.ejemploBootJpa.repositorios.EmpleadoCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CargoRestController
{
	@Autowired
	private CargoCRUDRepository cargoCRUDRepository;
	@Autowired
	private EmpleadoCRUDRepository empleadoCRUDRepository;
	@Autowired
	private PagedResourcesAssembler<Cargo> pagedResourcesAssembler;

	@Autowired
	private CargoModelAssembler cargoModelAssembler;

	

	@GetMapping("/cargos")
	public CollectionModel<Cargo> cargos()
	{
		Iterable<Cargo> cargos = getCargoCRUDRepository().findAll();
		for (Cargo cargo : cargos)
		{
			cargo.add(linkTo(methodOn(CargoRestController.class).cargosById(cargo.getId())).withSelfRel());
		}

		return CollectionModel.of(cargos);
	}

	@GetMapping("/cargos/{id}")

	public EntityModel<Cargo> cargosById(@PathVariable int id)
	{
		Cargo cargo = getCargoCRUDRepository().findById(id).get();
		cargo.add(linkTo(methodOn(CargoRestController.class).cargosById(cargo.getId())).withSelfRel());

		return EntityModel.of(cargo);
	}

	@GetMapping("/cargosAntiguo/{cantidad}/{pagina}/{orden}")
	public Iterable<Cargo> getCargosPaginadosAndOrdenados(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden)
	{
		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		return getCargoCRUDRepository().findAll(pageable);
	}

	

	@GetMapping("/cargosv1/{cantidad}/{pagina}/{orden}")
	public Page<Cargo> getCargosAllPageablev1(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<Cargo> cargos = getCargoCRUDRepository().findAll(pageable);
		
		for (Cargo cargo : cargos)
		{
			cargo.add(linkTo(methodOn(CargoRestController.class).cargosById(cargo.getId())).withSelfRel());
		}

		return cargos;
	}

	@GetMapping("/cargosv2/{cantidad}/{pagina}/{orden}")
	public PagedModel<EntityModel<Cargo>> getCargosAllPageablev2(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<Cargo> cargos = getCargoCRUDRepository().findAll(pageable);
		
		PagedModel<EntityModel<Cargo>> datosLaboralesPAginados=pagedResourcesAssembler.toModel(cargos,cargoModelAssembler);
		return datosLaboralesPAginados;
	}

	@DeleteMapping("/cargos/{id}")
	public String deleteCargosById(@PathVariable int id)
	{
		try
		{
			cargoCRUDRepository.deleteById(id);
			return "OK";
		} catch (EmptyResultDataAccessException e)
		{
			return "REGISTRO NO EXISTE";
		} catch (Exception e)
		{
			return "KO";
		}
	}

	@PostMapping("/cargos")
	public Optional<Cargo> altaModificaCargo(@RequestBody Cargo cargo)
	{

		getCargoCRUDRepository().save(cargo);
		return Optional.ofNullable(cargo);
	}

}
