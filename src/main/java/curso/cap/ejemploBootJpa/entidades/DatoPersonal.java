package curso.cap.ejemploBootJpa.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "DATOS_PERSONALES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RestResource(rel="datosPersonales", path="datosPersonales")
public class DatoPersonal extends RepresentationModel<DatoPersonal>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "HIJOS_ID")
	private Hijo hijo;
	
	@ManyToOne
	@JoinColumn(name = "ESTADOS_CIVILES_ID")
	private EstadoCivil estadoCivil;

	
	
}
