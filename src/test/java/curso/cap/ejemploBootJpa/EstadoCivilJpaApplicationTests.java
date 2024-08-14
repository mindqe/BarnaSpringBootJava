package curso.cap.ejemploBootJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.controladores.rest.EstadoCivilRestController;
import curso.cap.ejemploBootJpa.entidades.EstadoCivil;
import lombok.Data;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Data
@TestMethodOrder(MethodName.class)
public class EstadoCivilJpaApplicationTests {

	@Autowired
	private EstadoCivilRestController estadoCivilRestController;
	
	@Test
	public void averEstadoCivils() {
		Iterable<EstadoCivil> estadosCiviles= getEstadoCivilRestController().estadosCiviles();
		for (EstadoCivil EstadoCivil : estadosCiviles)
		{
			System.out.println(EstadoCivil.getDescripcion());
		}
		
		assertNotNull(estadosCiviles);
	}
	
	
	@Test
	public void baltaEstadoCivil() {
		
		EstadoCivil estadoCivil= new EstadoCivil();
		estadoCivil.setDescripcion("viudo");
		getEstadoCivilRestController().altaModificaEstadoCivil(estadoCivil);
		assertNotEquals(estadoCivil.getId(), 0);
	}
	
	@Test
	public void cborrarEstadoCivil() {
		EstadoCivil estadoCivil= new EstadoCivil();
		estadoCivil.setDescripcion("casado y con hijos");
		getEstadoCivilRestController().altaModificaEstadoCivil(estadoCivil);
		String estadoCivilBorrado = getEstadoCivilRestController().deleteEstadosCivilesById(estadoCivil.getId());
		assertEquals(estadoCivilBorrado, "OK");
		
	}

	@Test
	public void cconsultaEstadoCiviles() {
		EntityModel<EstadoCivil> estadoCivil = getEstadoCivilRestController().estadosCivilesById(2);
	
		System.out.println(estadoCivil.getContent().getDescripcion());
	
		assertNotNull(estadoCivil);
	}
	
	@Test
	public void cactualizaEstadoCivil() {
		EstadoCivil estadoCivil= new EstadoCivil();
		estadoCivil.setDescripcion("prometido");
		estadoCivil.setId(2);
		getEstadoCivilRestController().altaModificaEstadoCivil(estadoCivil);
		System.out.println(estadoCivil.getDescripcion());
		assertTrue(estadoCivil.getDescripcion().equals("prometido"));
	}
}
