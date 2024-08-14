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
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import curso.cap.ejemploBootJpa.controladores.rest.DatoPersonalRestController;
import curso.cap.ejemploBootJpa.entidades.DatoPersonal;
import curso.cap.ejemploBootJpa.entidades.EstadoCivil;
import curso.cap.ejemploBootJpa.entidades.Hijo;
import lombok.Data;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Data
@TestMethodOrder(MethodName.class)
public class Datos_personales_Test {

	@Autowired
	private DatoPersonalRestController datosPersonalesRestController;
	/*
	 * @Autowired private HijosCRUDRepository hijosCRUDRepository;
	 * 
	 * @Autowired private EstadosCivilesCRUDRepository estadosCivilesCRUDRepository;
	 */

	@Test
	public void AverDatos_personales() {
		Iterable<DatoPersonal> datosP= getDatosPersonalesRestController().datosPAll();
		for (DatoPersonal datop : datosP)
		{
			System.out.println(datop.getId()+": "+datop.getHijo()+":"+datop.getEstadoCivil());
		}
		assertNotNull(datosP);
	}
	  @Test
	  public void BaltaDatoPersonal() {
	  
		  EstadoCivil estadoCivil = new EstadoCivil();
		  estadoCivil.setId(1);
		  
		  
		  Hijo hijo = new Hijo();
		  hijo.setId(1);
		  
		  
		  DatoPersonal datoPersonal = new DatoPersonal();
		  datoPersonal.setId(0);
		  
		  datoPersonal.setEstadoCivil(estadoCivil);
		  datoPersonal.setHijo(hijo);
		  
		  getDatosPersonalesRestController().altaModificaDatoPersonal(datoPersonal);
		  assertNotEquals(datoPersonal.getId(), 0); 
		  
		  System.out.println(datoPersonal + " Dato insertado");
		  
		  dborrarDato(datoPersonal.getId());
		  
		  
		  assertThrows(NoSuchElementException.class,()->{
			  EntityModel<DatoPersonal> dPBorrado = getDatosPersonalesRestController().datosPById(datoPersonal.getId());
			  
		  });
		  
		  
	  }

	@Test
	public void consultaDatosP() {
		EntityModel<DatoPersonal> datoPer = getDatosPersonalesRestController().datosPById(1);
		
		System.out.println(datoPer.getContent());
		assertNotNull(datoPer);
	}
	
	public void dborrarDato(int id) {
		getDatosPersonalesRestController().deleteDatoPById(id);

	}
}
