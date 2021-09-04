package acme.testing.controlCheck;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AnonymousShoutListTest extends DP2Test{
	
	//Test cases ------------------------
	//Se prueba el listado de shouts desde el punto de vista de un anonimo,se prueban los paramtros : author,text,info de cada shout de la lista, 
	//se considera exitoso si se listan los shout con exito, el test debe dar exito
	@ParameterizedTest
	@CsvFileSource(resources = "/controlCheck/anonymous/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void  list(final int recordIndex, final String moment,final String author,  final String text, 
		final String xxxdate,final String xxxmoment,  final String xxxamount,final String xxxcurrency) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shouts list");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, xxxdate);
		super.checkColumnHasValue(recordIndex, 4, xxxmoment);
		super.checkColumnHasValue(recordIndex, 5, xxxamount);
		super.checkColumnHasValue(recordIndex, 6, xxxcurrency);


		
	}
	
	

}
