package curso.cap.ejemploBootJpa.entidades;

import java.util.GregorianCalendar;

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
@Table(name= "EMPLEADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RestResource(rel = "empleados",path = "empleados")

public class Empleado extends RepresentationModel<Empleado> implements Comparable<Empleado> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	private String dni;
	private String telefono;
	private GregorianCalendar fecha_nacimiento;
	private GregorianCalendar fecha_alta;
	private GregorianCalendar fecha_baja;
	
	@ManyToOne
	@JoinColumn(name = "DATOS_LABORALES_ID")
	private DatoLaboral datoLaboral;
	
	@ManyToOne
	@JoinColumn(name = "DATOS_PERSONALES_ID")
	private DatoPersonal datoPersonal;

	@Override
	public int compareTo(Empleado o)
	{
		// TODO Auto-generated method stub
		return this.getTelefono().compareTo(o.getTelefono());
	}
	
	
	
		
	
}
