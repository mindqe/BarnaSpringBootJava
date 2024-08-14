package curso.cap.ejemploBootJpa.repositorios;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.cap.ejemploBootJpa.entidades.Usuario;

@Repository
public interface UsuarioCRUDRepository extends CrudRepository<Usuario, Integer> {

	@Query("from Usuario u where u.usuario=:username")
	public Usuario getUserByUsername(String username);
}
