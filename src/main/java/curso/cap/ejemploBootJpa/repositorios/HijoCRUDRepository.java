package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curso.cap.ejemploBootJpa.entidades.Hijo;

@Repository
public interface HijoCRUDRepository extends CrudRepository<Hijo, Integer>,
PagingAndSortingRepository<Hijo, Integer>
{
	//@Query(value="select c.id.cargo c.descripcion from cargos c where c.descripcion like:name",nativeQuery=true)
	@Query(value = "from Hijo h where h.id like:id")
	public Iterable<Hijo> getHijosById(int id);
}
