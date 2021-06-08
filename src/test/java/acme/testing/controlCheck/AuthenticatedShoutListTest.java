package acme.testing.controlCheck;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedShoutListTest extends DP2Test{

//	En este se prueban los tests de tasks correspondientes a la lista de shouts desde la perspectiva de un ususario autenticado
//	los resultados para este test deben ser positivos dado que prueban si la lista de tasks que están en /authenticated/shout/list-positive.csv
//	se cumplen y así ocurre.
@ParameterizedTest
@CsvFileSource(resources = "/authenticated/shout/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(10)
public void authenticatedListPositive (final int recordIndex, final String moment, final String author, 
	final String text) {
	super.navigateHome();
	super.signIn("normal1", "normal1");
	super.clickOnMenu("Anonymous", "Shouts list");
	

	super.checkColumnHasValue(recordIndex, 0, moment);
	super.checkColumnHasValue(recordIndex, 1, author);
	super.checkColumnHasValue(recordIndex, 2, text);
	super.signOut();
	
	}
//En este se prueban los tests de tasks correspondientes a la lista de shouts desde la perspectiva de un ususario autenticado como manager
//los resultados para este test deben ser positivos dado que prueban si la lista de tasks que están en /authenticated/shout/list-positive.csv
//se cumplen y así ocurre.
@ParameterizedTest
@CsvFileSource(resources = "/authenticated/shout/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(20)
public void authenticatedListPositiveManager (final int recordIndex, final String moment, final String author, 
	final String text) {
	super.navigateHome();
	super.signIn("manager1", "manager1");
	super.clickOnMenu("Anonymous", "Shouts list");

	super.checkColumnHasValue(recordIndex, 0, moment);
	super.checkColumnHasValue(recordIndex, 1, author);
	super.checkColumnHasValue(recordIndex, 2, text);
	super.signOut();
	
	}
}