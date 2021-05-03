package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

//Declara esta interfaz como repositorio(no es un clase)
//El framework crea una clase e implementa a interfaz(es un singleton, solo hay una instancia de esa clase)
//Aplicamos herencia con AbstractRepository
@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
	//Consulta hacia la base de datos:
	// 1.- Analizar la query
	// 2.- Detectar errrores si los hubiera
	// 3.- Convertir instrucciones SQL
	// 4.- Mandar instrucciones SQL  al servidor de BD
	// 5.- Recoger los resultados
	// 6.- Transformarlos en entidades tipo shout
	//c7.- Acumularlas en una coleccion 
	// 8.- Devolverlas
	// Soporte completo
	@Query("SELECT s FROM Shout s")
	Collection<Shout> findMany();
}
