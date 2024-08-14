package curso.cap.ejemploBootJpa.dtos;

import java.util.List;

import curso.cap.ejemploBootJpa.entidades.Rol;
import lombok.Data;

@Data
public class UserDto {

	private String usuario;
	private String token;
	private List<Rol> roles;
	
}
