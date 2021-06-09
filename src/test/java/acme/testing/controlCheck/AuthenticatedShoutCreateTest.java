package acme.testing.controlCheck;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedShoutCreateTest extends DP2Test {
//	En este test se prueba la creación de diferentes shouts de un usuario autenticado
//	, los resultados de este test son exitosos, se crean diferentes shouts que deberían poder crearse
	
	@ParameterizedTest
	@CsvFileSource(resources = "/controlCheck/authenticated/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShout(final int recordIndex,final String author, final String text, final String info, final String xxxamount, final String xxxcurrency) {
		final String n=new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"/test"+recordIndex;
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxxdate", n);
		super.fillInputBoxIn("xxx.xxxamount", xxxamount+ xxxcurrency);
		super.clickOnSubmitButton("Shout!");
		super.checkErrorsExist();
		
	}
//	En este test se prueban las validaciones en la creación de diferentes shouts de un usuario autenticado
//	, los resultados de este test son exitosos, se cumplen las diferentes validaciones y no se crea ningún shout
//	Las diferentes validaciones que se prueban son:
//		-El texto no debe superar los 100 caracteres y no debe estar en blanco
//		-El autor no debe estar vacío y debe tener un tamaño de entre 5 y 25
//		-El link tiene que tener el formato de url
//		-Nada debe contener spam
	

	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/controlCheck/authenticated/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
//
//	@Order(20)
//	public void negativeCreateShout(final int recordIndex,final String author, final String text, final String info, final String xxxamount, final String xxxcurrency) {
//		final String n=new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"/test"+recordIndex;
//		super.navigateHome();
//		super.clickOnMenu("Anonymous", "Shout!");
//		super.fillInputBoxIn("author", author);
//		super.fillInputBoxIn("text", text);
//		super.fillInputBoxIn("info", info);
//		super.fillInputBoxIn("xxx.xxxdate", n);
//		super.fillInputBoxIn("xxx.xxxamount", xxxamount+ xxxcurrency);
//		super.clickOnSubmitButton("Shout!");
//		super.checkErrorsExist();
//		
//	}

	@ParameterizedTest
	@CsvFileSource(resources = "/controlCheck/authenticated/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShoutManager( final int recordIndex,final String author, final String text, final String info, final String xxxamount, final String xxxcurrency) {
		final String n=new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"/test"+recordIndex;
		super.navigateHome();
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxxdate", n);
		super.fillInputBoxIn("xxx.xxxamount", xxxamount+ xxxcurrency);
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "Shouts list");
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, n);
		super.checkColumnHasValue(recordIndex, 5, xxxamount);
		super.checkColumnHasValue(recordIndex, 6, xxxcurrency);
		
	}
}