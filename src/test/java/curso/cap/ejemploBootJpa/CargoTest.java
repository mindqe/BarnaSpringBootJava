package curso.cap.ejemploBootJpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.controladores.rest.CargoRestController;
import curso.cap.ejemploBootJpa.entidades.Cargo;
import lombok.Data;

@Data
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodName.class)
@SpringBootTest
class CargoTest {
		
	@Autowired
	private CargoRestController cargoRestController;
		
	@Test
	public void averCargos() {
		Iterable<Cargo> cargos= getCargoRestController().cargos();
		for (Cargo cargo : cargos)
		{
			System.out.println(cargo.getId()+": "+cargo.getDescripcion());
		}
		assertNotNull(cargos);
	}
	@Test
	public void baltaCargo() {
		Cargo cargo = new Cargo();
		cargo.setDescripcion("Jefe de Proyecto de java de prueba1");
		getCargoRestController().altaModificaCargo(cargo);
		assertNotEquals(cargo.getId(), 0);

	}
	
	@Test
	public void bbajaCargo() {
		Cargo cargo = new Cargo();
		cargo.setDescripcion("Jefe de Proyecto de java de prueba2");
		getCargoRestController().altaModificaCargo(cargo);		
		String cargoBorrado = getCargoRestController().deleteCargosById(cargo.getId());
		assertEquals(cargoBorrado,"OK");
	}
	
	@Test
	public void cconsultaId() {
		EntityModel<Cargo> cargo = getCargoRestController().cargosById(2);
		
		System.out.println(cargo.getContent().getDescripcion());
		
		assertNotNull(cargo);
	}
}
