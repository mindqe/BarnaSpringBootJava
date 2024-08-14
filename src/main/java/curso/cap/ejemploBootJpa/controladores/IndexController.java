package curso.cap.ejemploBootJpa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import curso.cap.ejemploBootJpa.repositorios.DatoLaboralCRUDRepository;
import curso.cap.ejemploBootJpa.repositorios.EmpleadoCRUDRepository;
import lombok.Data;

@Controller
@Data
public class IndexController {
	
	@Autowired
	private DatoLaboralCRUDRepository datoLaboralCRUDRepository;
	
	@Autowired
	private EmpleadoCRUDRepository empleadoCRUDRepository;
	
	@RequestMapping("/login")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("datosLaborales", getDatoLaboralCRUDRepository().findAll());
		modelAndView.addObject("empleados", getEmpleadoCRUDRepository().findAll());
		
		return modelAndView;
	}
	
	@RequestMapping("/segura")
	public String segura() {
		return "segura";
	}
	
	@RequestMapping("/nologin")
	public ModelAndView error() {
		ModelAndView modelAndView= new ModelAndView("index");
		modelAndView.addObject("error"," usuario o clave no son validos");
		return modelAndView;
	}
	
	
	
	
	
}