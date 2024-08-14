package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import curso.cap.ejemploBootJpa.entidades.DatoLaboral;


@Repository
public interface DatoLaboralCRUDRepository extends CrudRepository<DatoLaboral, Integer>, PagingAndSortingRepository<DatoLaboral, Integer>{

	@Query(value = "from DatoLaboral d where cargos_id like:id")
	public void econsultaSalarioByCargoId(Integer id);
	
	@Query(value = "from DatoLaboral d where salario like:salario")
	public void fconsultaSalariosIguales(Integer salario);
	  
}
