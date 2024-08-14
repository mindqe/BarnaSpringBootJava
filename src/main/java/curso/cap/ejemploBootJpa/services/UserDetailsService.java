package curso.cap.ejemploBootJpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.GracefulShutdownCallback;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import curso.cap.ejemploBootJpa.entidades.Rol;
import curso.cap.ejemploBootJpa.entidades.Usuario;
import curso.cap.ejemploBootJpa.repositorios.UsuarioCRUDRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UsuarioCRUDRepository usuarioCRUDRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario= getUsuarioCRUDRepository().getUserByUsername(username);
		List<GrantedAuthority> roles= new ArrayList<GrantedAuthority>();
		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getDescripcion()));
		}
		
		
		return new User(usuario.getUsuario(), usuario.getClave(), usuario.isEnable(), true, true, true, roles);
	}

}
