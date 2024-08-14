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

import curso.cap.ejemploBootJpa.controladores.paginado.HijoModelAssembler;
import curso.cap.ejemploBootJpa.entidades.Hijo;
import curso.cap.ejemploBootJpa.repositorios.HijoCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HijoRestController
{
	@Autowired
	private HijoCRUDRepository hijoCRUDRepository;
	
	@Autowired
	private PagedResourcesAssembler<Hijo> pagedResourcesAssembler;

	@Autowired
	private HijoModelAssembler hijoModelAssembler;
	
	
	@GetMapping("/hijos")
	public CollectionModel<Hijo> hijos()
	{
		Iterable<Hijo> hijos = getHijoCRUDRepository().findAll();
		for (Hijo hijo : hijos)
		{
			hijo.add(linkTo(methodOn(HijoRestController.class).hijosById(hijo.getId())).withSelfRel());
		}

		return CollectionModel.of(hijos);
	}

	@GetMapping("/hijos/{id}")
	public EntityModel<Hijo> hijosById(@PathVariable int id)
	{
		Hijo hijo = getHijoCRUDRepository().findById(id).get();
		hijo.add(linkTo(methodOn(HijoRestController.class).hijosById(hijo.getId())).withSelfRel());

		return EntityModel.of(hijo);
	}
	
	@GetMapping("/hijosAntiguo/{cantidad}/{pagina}/{orden}")
	public Iterable<Hijo> getHijosPaginadosAndOrdenados(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden)
	{
		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		return getHijoCRUDRepository().findAll(pageable);
	}

	
	@GetMapping("/hijos/{cantidad}/{pagina}/{orden}")
	public Page<Hijo> getHijosAllPageable(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<Hijo> hijos = getHijoCRUDRepository().findAll(pageable);
		
		for (Hijo hijo : hijos)
		{
			hijo.add(linkTo(methodOn(HijoRestController.class).hijosById(hijo.getId())).withSelfRel());
		}

		return hijos;

	}
	
	@GetMapping("/hijosv2/{cantidad}/{pagina}/{orden}")
	public PagedModel<EntityModel<Hijo>> getHijosAllPageablev2(@PathVariable int cantidad, @PathVariable int pagina,
			@PathVariable String orden )
	{

		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		Page<Hijo> hijos = getHijoCRUDRepository().findAll(pageable);
		
		PagedModel<EntityModel<Hijo>> datosLaboralesPAginados=pagedResourcesAssembler.toModel(hijos, hijoModelAssembler);
		return datosLaboralesPAginados;

	}
	

	@DeleteMapping("/hijos/{id}")
	public String deleteHijosById(@PathVariable int id)
	{
		try
		{
			getHijoCRUDRepository().deleteById(id);
			return "OK";
		}catch(EmptyResultDataAccessException e) {
			return "REGISTRO NO EXISTE";
		}
		catch (Exception e)
		{
			return "KO";
		}
	}

	@PostMapping("/hijos")
	public Optional<Hijo> altaModificaHijo(@RequestBody Hijo hijo)
	{
		getHijoCRUDRepository().save(hijo);

		return Optional.ofNullable(hijo);
	}

}
