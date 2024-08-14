package curso.cap.ejemploBootJpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.GregorianCalendar;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.controladores.rest.EmpleadosControllerRest;
import curso.cap.ejemploBootJpa.entidades.DatoLaboral;
import curso.cap.ejemploBootJpa.entidades.DatoPersonal;
import curso.cap.ejemploBootJpa.entidades.Empleado;
import lombok.Data;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Data
@TestMethodOrder(MethodName.class)
public class EmpleadoTest {

	@Autowired
	private EmpleadosControllerRest empleadoControllerRest;
	
	
	public Optional<Empleado> getEmpleado(int id) {
		
		try {
			return Optional.ofNullable(getEmpleadoControllerRest().empleadoById(id).getContent());
		} catch (Exception e) {
			return Optional.empty();
		}
		
	}
	
	
	@Test
	public void testGeneral() {
		this.verEmpleados();
		Empleado empleado = null;
		int idEmpleado = aAltaEmpleado(empleado);
		bModificarEmpleado(idEmpleado);
		cBorrarEmpleado(idEmpleado);
		
		
	}
	
	
	public void verEmpleados() {
		Iterable<Empleado> empleados = getEmpleadoControllerRest().empleados();
		for (Empleado empleado : empleados)
		{
			System.out.println(empleado.getNombre());
		}
		
		assertNotNull(empleados);
	}
	
	public int aAltaEmpleado(Empleado empleado) {
		empleado = new Empleado();
		empleado.setDni("1234");
		empleado.setNombre("empleado2");
		
		DatoLaboral datoLaboral = new DatoLaboral();
		datoLaboral.setId(1);
		empleado.setDatoLaboral(datoLaboral);
		
		DatoPersonal datoPersonal = new DatoPersonal();
		datoPersonal.setId(1);
		empleado.setDatoPersonal(datoPersonal);
		
		empleado.setFecha_alta(new GregorianCalendar(2021, 3, 22));
		empleado.setFecha_nacimiento(new GregorianCalendar(1999, 3, 22));
		empleado.setTelefono("1234");
		
		getEmpleadoControllerRest().altaModificaEmpleado(empleado);
		assertNotEquals(empleado.getId(), 0);
		
		return empleado.getId();
	}
	
	public void bModificarEmpleado(int id) {	
		
		Optional<Empleado> empleado = getEmpleado(id);
		assertTrue(empleado.isPresent());
		empleado.get().setNombre("LEL");
		empleado =  getEmpleadoControllerRest().altaModificaEmpleado(empleado.get());
		assertTrue(empleado.isPresent());
		
		
		Optional<Empleado> empleadoModificado = getEmpleado(id);
		assertTrue(empleadoModificado.isPresent());
		Empleado empleadoNuevo = empleadoModificado.get();
		assertTrue(empleadoNuevo.getNombre().equals("LEL"));
	}
	
	public void cBorrarEmpleado(int id) {
		Optional<Empleado> empleadoOriginal = getEmpleado(id);
		assertTrue(empleadoOriginal.isPresent());
		
		Empleado empleado = empleadoOriginal.get();
		String respuesta = getEmpleadoControllerRest().deleteEmpleadoById(empleado.getId());
		assertTrue(respuesta.equals("OK"));
		Optional<Empleado> empleadoBorrado = getEmpleado(id);
		assertFalse(empleadoBorrado.isPresent());
	}

}