package curso.cap.ejemploBootJpa.controladores.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.cap.ejemploBootJpa.controladores.paginado.DatoLaboralModelAssembler;
import curso.cap.ejemploBootJpa.entidades.DatoLaboral;
import curso.cap.ejemploBootJpa.repositorios.CargoCRUDRepository;
import curso.cap.ejemploBootJpa.repositorios.DatoLaboralCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DatoLaboralRestController
{

	@Autowired
	private DatoLaboralCRUDRepository datoLaboralCRUDRepository;
	@Autowired
	private CargoCRUDRepository cargoCRUDRepository;
	@Autowired
    private PagedResourcesAssembler<DatoLaboral> pagedResourcesAssembler;
	@Autowired
	private DatoLaboralModelAssembler datoLaboralModelAssembler;
	
	@GetMapping("/datosLaborales/{cantidad}/{pagina}/{orden}")
	public PagedModel<EntityModel<DatoLaboral>> datosLaborales(@PathVariable int cantidad, @PathVariable int pagina, @PathVariable String orden){
		 
		Sort order = Sort.by(Order.asc(orden));
		Pageable pageable = PageRequest.of(pagina, cantidad, order);
		
		Page<DatoLaboral> datosLaborales = getDatoLaboralCRUDRepository().findAll(pageable);
		
		PagedModel<EntityModel<DatoLaboral>> datosLaboralesPaginados = 
				pagedResourcesAssembler.toModel(datosLaborales, getDatoLaboralModelAssembler());
		
		return datosLaboralesPaginados;
	}
	
	@GetMapping("/datosLaborales/{id}")
	public EntityModel<DatoLaboral> datoLaboralById(@PathVariable int id){
		
			DatoLaboral datoLaboral = datoLaboralCRUDRepository.findById(id).get();
			datoLaboral.add(linkTo(methodOn(DatoLaboralRestController.class).datoLaboralById(datoLaboral.getId())).withSelfRel());
			datoLaboral.add(linkTo(methodOn(CargoRestController.class).cargosById(datoLaboral.getCargo().getId())).withRel("cargo"));
			
		return EntityModel.of(datoLaboral);
	}
	
	@DeleteMapping("/datosLaborales/{id}")
	public String borrarDatoLaboral(@PathVariable int id) {
		try {
			datoLaboralCRUDRepository.deleteById(id);
			return "OK";
		} catch (EmptyResultDataAccessException e)
		{
			return "REGISTRO NO EXISTE";
		} catch (Exception e)
		{
			return e.toString();
		}
	}

	@PostMapping("/datosLaborales")
	public EntityModel<DatoLaboral> altaModificarDatoLaboral(@RequestBody DatoLaboral datoLaboral){
		
		System.out.println(datoLaboral);
		
		getDatoLaboralCRUDRepository().save(datoLaboral);
		datoLaboral.add(linkTo(methodOn(DatoLaboralRestController.class).datoLaboralById(datoLaboral.getId())).withSelfRel());
		datoLaboral.add(linkTo(methodOn(CargoRestController.class).cargosById(datoLaboral.getCargo().getId())).withRel("cargo"));
		
		return EntityModel.of(getDatoLaboralCRUDRepository().findById(datoLaboral.getId()).get());

	}
}

