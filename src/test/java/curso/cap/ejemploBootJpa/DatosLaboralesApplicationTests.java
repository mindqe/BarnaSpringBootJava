package curso.cap.ejemploBootJpa;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;


import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.controladores.rest.DatoLaboralRestController;
import curso.cap.ejemploBootJpa.entidades.Cargo;
import curso.cap.ejemploBootJpa.entidades.DatoLaboral;
import lombok.Data;

@Data
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodName.class)
public class DatosLaboralesApplicationTests {
	
	@Autowired
	private DatoLaboralRestController datoLaboralRestController;
	
	@Autowired
	private CargoRestController cargoRestController;

	
	@Test
	public void ainsertarModificarDatoLaboral() {
		
		DatoLaboral datoLaboral = new DatoLaboral();
		datoLaboral.setSalario(3000);
		datoLaboral.setCargo(cargoRestController.cargosById(1).getContent());
		getDatoLaboralRestController().altaModificarDatoLaboral(datoLaboral);
		
		System.out.println(datoLaboral.getId());
		assertNotEquals(datoLaboral.getId(), 0);
	}
	
	@Test
	public void dRecuperarPorId() {   

		DatoLaboral datoLaboral = getDatoLaboralRestController().datoLaboralById(2).getContent();

		System.out.println(datoLaboral);
		assertNotNull(datoLaboral);
	}
	
	@Test
	public void dRecuperarTodos() {   

		Iterable<EntityModel<DatoLaboral>> datosLaborales = getDatoLaboralRestController().datosLaborales(5, 0, "salario");
		for (EntityModel<DatoLaboral> datoLaboral : datosLaborales) {

			System.out.println(datoLaboral);
		}
		assertNotNull(datosLaborales);
	}
	
	
	@Test
	public void eborrarDatoLaboral() {


		Cargo cargo = getCargoRestController().cargosById(5).getContent();
		DatoLaboral datoLaboral = new DatoLaboral();
		datoLaboral.setCargo(cargo);
		datoLaboral.setSalario(15000);
		

		
		getDatoLaboralRestController().altaModificarDatoLaboral(datoLaboral);
		getDatoLaboralRestController().borrarDatoLaboral(datoLaboral.getId());
		
		assertThrows(NoSuchElementException.class, () -> {
			@SuppressWarnings("unused")
			EntityModel<DatoLaboral> datoLaboralBorrado = getDatoLaboralRestController().datoLaboralById(datoLaboral.getId());
		});

	} 
}
