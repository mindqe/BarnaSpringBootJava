package curso.cap.ejemploBootJpa.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HIJOS")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@RestResource(rel = "hijos",path = "hijos")
public class Hijo extends RepresentationModel <Hijo>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int chicos;
	private int chicas;

}
