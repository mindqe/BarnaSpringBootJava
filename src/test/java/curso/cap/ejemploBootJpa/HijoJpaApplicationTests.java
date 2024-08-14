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

import curso.cap.ejemploBootJpa.controladores.rest.HijoRestController;
import curso.cap.ejemploBootJpa.entidades.Hijo;
import lombok.Data;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Data
@TestMethodOrder(MethodName.class)
public class HijoJpaApplicationTests {
	@Autowired
	private HijoRestController hijoRestController;
	
	@Test
	public void averHijos() {
		Iterable<Hijo> hijos= getHijoRestController().hijos();
		for (Hijo hijo : hijos)
		{
			System.out.println(hijo.getChicos());
			System.out.println(hijo.getChicas());
		}
		
		assertNotNull(hijos);
	}
	
	@Test
	public void baltahijo() {
		
		Hijo hijo= new Hijo();
		hijo.setChicos(11);
		hijo.setChicas(14);
		getHijoRestController().altaModificaHijo(hijo);
		assertNotEquals(hijo.getId(), 0);
		
	}
	
	@Test
	public void cborrarHijo() {
		Hijo hijo= new Hijo();
		hijo.setChicos(423);
		hijo.setChicas(144);
		getHijoRestController().altaModificaHijo(hijo);
		String hijoBorrado = getHijoRestController().deleteHijosById(hijo.getId());
		assertEquals(hijoBorrado,"OK");
		
	}

	@Test
	public void cconsultaHijos() {
		EntityModel<Hijo> hijo = getHijoRestController().hijosById(2);
		System.out.println(hijo.getContent().getChicos());
		assertNotNull(hijo);
	}
	
	@Test
	public void cactualizaChicos() {
		Hijo hijo= new Hijo();
		hijo.setChicos(90);
		hijo.setId(2);
		getHijoRestController().altaModificaHijo(hijo);
		System.out.println(hijo.getChicos());
		assertTrue(hijo.getChicos() == 90);
	}
}
