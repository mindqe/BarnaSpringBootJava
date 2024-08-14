package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import curso.cap.ejemploBootJpa.entidades.DatoPersonal;

@Repository
public interface DatosPersonalesCRUDRepository extends CrudRepository<DatoPersonal, Integer>, 
PagingAndSortingRepository<DatoPersonal, Integer>
{
	//@Query(value="select id, datos_civiles_id, hijos_id from datos_personales like:id ",nativeQuery=true)
	@Query(value = "from DatoPersonal d where d.id like:id")
	public Iterable<DatoPersonal> getDatoPersonal(int id);
}
