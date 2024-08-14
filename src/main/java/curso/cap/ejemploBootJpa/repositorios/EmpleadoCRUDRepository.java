package curso.cap.ejemploBootJpa.repositorios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curso.cap.ejemploBootJpa.entidades.Empleado;

@Repository
public interface EmpleadoCRUDRepository extends CrudRepository<Empleado, Integer>, PagingAndSortingRepository<Empleado, Integer> {

	public default Stream<Empleado> empledosByYear(int year){
		
		ArrayList<Empleado> empleados=(ArrayList<Empleado>) findAll();
		
		MyFunctionalInterface myFunctionalInterface=e->e.stream().
				filter(empleado->empleado.getFecha_nacimiento().get(Calendar.YEAR)>=year);
		
		return  myFunctionalInterface.getEmpleadosByYear(empleados);
	}

	
	public Optional<Empleado> findOneByDni(String dni);
}
