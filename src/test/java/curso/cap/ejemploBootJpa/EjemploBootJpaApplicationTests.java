package curso.cap.ejemploBootJpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.entidades.Cargo;
import curso.cap.ejemploBootJpa.repositorios.CargoCRUDRepository;
import lombok.Data;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Data
@TestMethodOrder(MethodName.class)
class EjemploBootJpaApplicationTests {

	@Autowired
	private CargoCRUDRepository cargoCRUDRepository;
	 
	
	@Test
	public void averCargos() {
		Iterable<Cargo> cargos= getCargoCRUDRepository().findAll();
		for (Cargo cargo : cargos)
		{
			System.out.println(cargo.getDescripcion());
		}
		
		assertNotNull(cargos);
	}
	
	@Test
	public void baltaCargo() {
		
		Cargo cargo= new Cargo();
		cargo.setDescripcion("Jefe de Proyecto de java de prueba");
		getCargoCRUDRepository().save(cargo);
		assertNotEquals(cargo.getId(),0);
		cborrarCargo(cargo.getId());
		Optional<Cargo> cargoBorrado=getCargoCRUDRepository().findById(cargo.getId());
		assertFalse(cargoBorrado.isPresent());
		
	}
	
	public void cborrarCargo(int id) {
		getCargoCRUDRepository().deleteById(id);
	}

	@Test
	public void cconsultaNombre() {
		Iterable<Cargo> cargos = getCargoCRUDRepository().getCargosByName("Jefe%");
		for (Cargo cargo : cargos) {
			System.out.println(cargo.getDescripcion());
		}
		assertNotNull(cargos);
	}
	
	@Test
	public void cactualizaNombre() {
		Cargo cargo= new Cargo();
		cargo.setDescripcion("Jefe de Proyecto de java editado");
		cargo.setId(2);
		getCargoCRUDRepository().save(cargo);
		System.out.println(cargo.getDescripcion());
		assertTrue(cargo.getDescripcion().equals("Jefe de Proyecto de java editado"));
	}
	
	
}
