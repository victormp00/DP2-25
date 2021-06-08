package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AnonymousShoutCreateTest extends DP2Test {
	
	//Se prueba la creacion de un shout desde el punto de vista de un anonimo,se prueban los paramtros : author,text,info  se considera exitoso si se crea el shout con exito, el test debe dar exito
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final int recordIndex,final String author, final String text, final String info,final String xxxdate, final String xxxmoney) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxxdate", xxxdate);
		super.fillInputBoxIn("xxx.xxxamount", xxxmoney);
		super.clickOnSubmitButton("Shout!");
		
		
		
	
	}
	//Se prueba la creacion de un shout desde el punto de vista de un anonimo,se prueban los paramtros : author: el cual no puede ni estar en blanco, ni contener pàlabras spam, 
	//text: no puede ni estar en blanco,ni contener pàlabras spam,info : no puede ni estar en blanco,ni contener pàlabras spam 
	//Se considera exitoso si no se crea el shout con exito, el test debe dar exito
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShout(final int recordIndex,final String author, final String text, final String info,final String xxxdate, final String xxxmoney) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxxdate", xxxdate);
		super.fillInputBoxIn("xxx.xxxamount", xxxmoney);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	
	
	
	
	
	
	
}