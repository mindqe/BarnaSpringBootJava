package curso.cap.ejemploBootJpa.controladores.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.cap.ejemploBootJpa.dtos.UserDto;
import curso.cap.ejemploBootJpa.entidades.Rol;
import curso.cap.ejemploBootJpa.entidades.Usuario;
import curso.cap.ejemploBootJpa.repositorios.UsuarioCRUDRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@RestController
@Data
public class UserRestController {

	@Autowired
	private UsuarioCRUDRepository usuarioCRUDRepository;
	
	@PostMapping("/user")
	@CrossOrigin(origins = "*")
	public UserDto getToken(@RequestParam  String usuario,@RequestParam String clave) {
		UserDto salida= new UserDto();
		
		Usuario user=getUsuarioCRUDRepository().getUserByUsername(usuario);
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(4);
		boolean resultado= encoder.matches(clave, user.getClave());
		if(user.getUsuario().equalsIgnoreCase(usuario) && resultado) {
			salida.setToken(getToken(usuario, user.getRoles()));
			salida.setUsuario(user.getUsuario());
			salida.setRoles(user.getRoles());
		}
		
		return salida;
		
	}

	private String getToken(String usuario, List<Rol> roles) {
		
		
		
		String securityKey="capcap";

        String token= Jwts.builder().setId(securityKey).setSubject(usuario).claim("roles",roles).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+600000)).
                signWith(SignatureAlgorithm.HS512,securityKey.getBytes()).compact();
        return "Bearer "+token;
        
        
	}
}
