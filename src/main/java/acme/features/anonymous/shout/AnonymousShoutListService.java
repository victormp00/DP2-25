package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

//Esta clase implementa un servicio, debe instanciar y 
//hallar la instancia para que al encontrarla la debe inyectar.
//Indicar el rol (anonymous) y tipo de dato (shout)
@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {
	//Declarar un repositorio, puede ser privado, protected, publico o de paquete.
	//Nada de lo que hacemos es tipo de datos, son modulos.
	//Internal state
	@Autowired
	protected AnonymousShoutRepository repository;
	
	
	//Este metodo nos permite escribir las restricciones de autorizacion de una peticion.
	//Recibe un request sobre un objeto (shout)
	//Comprobar que el usuario es el correcto
	//El framework hace esa verificacion
	
	//AbstractListService<Adminsitrator, Shout> interface
	@Override
	public boolean authorise(final Request<Shout> request){
		assert request != null;
		return true;
	}
	
	//unbind: mostrar un listado de objetos(generar la vista)
	//Especificar exactamente que atributos de la entidad tengo que sacar al modelo para crear 
	//el html. 
	//
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text","moment");
	}
	
	//buscar todos los objetos que mostrare en el listado.
	//Metodo buscar mucho,Request parametro de entrada. hace un bypass hacia el 
	//repositorio para sacar los datos.
	@Override
	public Collection<Shout> findMany(final Request<Shout> request){
		assert request != null;
		Collection<Shout> result;
		result = this.repository.findMany();
		return result;
	}
}
 