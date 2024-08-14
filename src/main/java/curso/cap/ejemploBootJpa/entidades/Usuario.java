package curso.cap.ejemploBootJpa.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String usuario;
	private String clave;
	private boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIOS_HAS_ROLES",joinColumns = @JoinColumn(name = "USUARIOS_ID"), inverseJoinColumns = @JoinColumn(name = "ROLES_ID"))
	private List<Rol> roles;

}
