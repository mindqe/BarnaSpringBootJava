package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import curso.cap.ejemploBootJpa.entidades.Cargo;

@Repository
public interface CargoCRUDRepository extends CrudRepository<Cargo, Integer>,
PagingAndSortingRepository<Cargo, Integer>
{

	//@Query(value="select c.id.cargo c.descripcion from cargos c where c.descripcion like:name",nativeQuery=true)
	@Query(value = "from Cargo c where c.descripcion like:name")
	@Transactional
	public Iterable<Cargo> getCargosByName(String name);
}
