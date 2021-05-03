package acme.features.anonymous.shout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.shouts.Shout;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

//Controller: Declara la clase como un controlador, es decir recibira peticiones desde el servidor de servlet, 
//solo habra una instancia(solo un objeto, y con eso basta) de esa clase. Implementa un workflow para atender una peticion.
//Pueden haber muchas peticiones diferentes a un solo controlador.
//Este es un controlador (/anonymous/shouts/)
//RequestMapping: Declara las peticiones y a donde van cada una de ellas(las URL definidas), depende donde haya sido desplegada la aplicacion.
//AbstractController: aplicaremos herencia sobre esta clase, que ya define todo. Solo le pasamos el rol y el tipo de objeto, es decir 
//Rol: anonymous y tipo objeto sobre el que trabajare: shout.

@Controller
@RequestMapping("/anonymous/shout/")
public class AnonymousShoutController extends AbstractController<Anonymous,Shout>{
	
	//Internal state
	//Servicio de apoyo para listar Shouts, se declara como un objeto.Usamos clase como modulo.
	//Autowired: Responsabilizamos al framework para que cree el servicio de listado.El controlador 
	//no crea el servicio de listado, el framwork analiza el codigo, crea instancia del servicio y cuando 
	//esta preparada lo inyecta en el atributo.
	
	@Autowired
	private AnonymousShoutListService listService;
	
	@Autowired
	private AnonymousShoutCreateService createService;
	
	//addBasicCommand: declara que el controlador se encarga de las peticiones List, y con ayuda del servicio creado.
	//PostConstructor: Se ejecute con posterioridad del constructor, tienen valores por defecto.Al crearse no sera nulo.
	//Constructors. Referencia a servicio correspondiente.
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
