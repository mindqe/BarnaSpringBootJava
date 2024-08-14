package curso.cap.ejemploBootJpa.controladores.rest;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.cap.ejemploBootJpa.entidades.Empleado;
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
public class EmpleadosControllerRest {

	@Autowired
	private EmpleadoCRUDRepository empleadoCRUDRepository;

	@GetMapping("/empleados")
	public Iterable<Empleado> empleados() {
		return getEmpleadoCRUDRepository().findAll();
	}

	@GetMapping("/empleado/{id}")
	public EntityModel<Empleado> empleadoById(@PathVariable int id) {
		Empleado empleado = getEmpleadoCRUDRepository().findById(id).get();
		empleado.add(linkTo(methodOn(EmpleadosControllerRest.class).empleadoById(empleado.getId())).withSelfRel());
		empleado.add(linkTo(methodOn(DatoLaboralRestController.class).datoLaboralById(empleado.getDatoLaboral().getId())).withSelfRel());
		empleado.add(linkTo(methodOn(DatoPersonalRestController.class).datosPById(empleado.getDatoPersonal().getId())).withSelfRel());
		
		return EntityModel.of(empleado);
	}

	@PostMapping("/empleados")
	public Optional<Empleado> altaModificaEmpleado(@RequestBody Empleado empleado) {
		getEmpleadoCRUDRepository().save(empleado);

		return Optional.ofNullable(empleado);
	}

	@DeleteMapping("/empleado/{id}")
	public String deleteEmpleadoById(@PathVariable int id) {
		try {
			getEmpleadoCRUDRepository().deleteById(id);
			return "OK";
		} catch (EmptyResultDataAccessException e) {
			return "REGISTRO NO EXISTE";
		} catch (Exception e) {
			return "KO";
		}
	}

	@GetMapping("empleados/{size}/{page}/{order}")
	public Page<Empleado> showEmpleados(@PathVariable int size, @PathVariable int page, @PathVariable String order) {

		Page<Empleado> empleados = getEmpleadoCRUDRepository()
				.findAll(PageRequest.of(page, size, Sort.by(Order.asc(order))));

		for (Empleado empleado : empleados) {
			empleado.add(linkTo(methodOn(EmpleadosControllerRest.class).empleadoById(empleado.getId())).withSelfRel());
			empleado.add(linkTo(methodOn(DatoLaboralRestController.class).datoLaboralById(empleado.getDatoLaboral().getId())).withSelfRel());
			empleado.add(linkTo(methodOn(DatoPersonalRestController.class).datosPById(empleado.getDatoPersonal().getId())).withSelfRel());
			
		}

		return empleados;
	}

}
