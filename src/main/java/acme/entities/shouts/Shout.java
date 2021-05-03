package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

//Entity: Declara la clase como tipo entidad, los objetos dentro de ella son persistentes(BD)
//Getter y Setter: métodos como anotaciones automaticamente se generan para todos los atributos
//Usamos herencia para tener algunas funciones y ya no escribirlas
@Entity
@Getter
@Setter
public class Shout extends DomainEntity {
	//REMORA (algo antiguo con lo que debo cargar pero no tiene ninguna utilidad) 
	//de las primeras versiones de java.
	//Serialisation identifier (para poder sacar los objetos fuera de la clase)
	protected static final long serialVersionUID = 1L;
	
	//Attributes (tomados de los requisitos)
		//Temporal: almacena tanto la fecha como la hora.
		@Temporal(TemporalType.TIMESTAMP)
		@Past
		@NotNull
		protected Date moment;
		
		@NotBlank
		protected String author;
		
		@NotBlank
		protected String text;
		
		//Puede ser nula y si tiene una cadena debe ser del tipo URL.
		@URL
		protected String info;
}
