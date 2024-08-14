package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curso.cap.ejemploBootJpa.entidades.EstadoCivil;

@Repository
public interface EstadoCivilCRUDRepository extends CrudRepository<EstadoCivil, Integer>, 
PagingAndSortingRepository<EstadoCivil, Integer>
{
	@Query(value = "from EstadoCivil e where e.id like:id")
	public Iterable<EstadoCivil> getEstadoCivilById(int id);
}
