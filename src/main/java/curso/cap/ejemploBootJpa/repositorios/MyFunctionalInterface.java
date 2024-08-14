package curso.cap.ejemploBootJpa.repositorios;

import java.util.ArrayList;
import java.util.stream.Stream;

import curso.cap.ejemploBootJpa.entidades.Empleado;

@FunctionalInterface
public interface MyFunctionalInterface
{

	/*
	 * public int hola(int hola);
	 * 
	 * default void a() {
	 * 
	 * }
	 */
	
	public   Stream<Empleado> getEmpleadosByYear(ArrayList<Empleado> empleados);
}
