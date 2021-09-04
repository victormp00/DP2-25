package acme.testing.controlCheck;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AnonymousShoutCreateTest extends DP2Test {
	
	//Se prueba la creacion de un shout desde el punto de vista de un anonimo,se prueban los paramtros : author: el cual no puede ni estar en blanco, ni contener pàlabras spam, 
		//text: no puede ni estar en blanco,ni contener pàlabras spam,info : no puede ni estar en blanco,ni contener pàlabras spam 
		//Se considera exitoso si no se crea el shout con exito, el test debe dar exito
		@ParameterizedTest
		@CsvFileSource(resources = "/controlCheck/anonymous/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeCreateShout(final int recordIndex,final String author, final String text,
			final String info, final String xxxamount, final String xxxcurrency, final String xxxmoment) {
			final String n=new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"/test";
			
			
			
			super.navigateHome();
			super.clickOnMenu("Anonymous", "Shout!");
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			super.fillInputBoxIn("xxx.xxxdate", n);
			super.fillInputBoxIn("xxx.xxxmoment", xxxmoment);
			super.fillInputBoxIn("xxx.xxxamount", xxxamount+ xxxcurrency);
			super.clickOnSubmitButton("Shout!");
			super.checkErrorsExist();
			
		}
	
	//Se prueba la creacion de un shout desde el punto de vista de un anonimo,se prueban los paramtros : author,text,info  se considera exitoso si se crea el shout con exito, el test debe dar exito
	@ParameterizedTest
	@CsvFileSource(resources = "/controlCheck/anonymous/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final int recordIndex,final String author, final String text,
		final String info,final String xxxamount, final String xxxcurrency) {
		
		final Date actual= new Date();
		final Integer d= actual.getDate();
		final Integer m=actual.getMonth()+1;
		final Integer y=actual.getYear()+1900;
		String month= m.toString();
		String day= d.toString();
		if(m<10) {
			month="0"+month;
		}
		if(d<10) {
			day="0"+day;
		}
		final String n = day+"/"+month+"/"+y.toString()+"/test"+recordIndex;
		
		
		actual.setDate(actual.getDate()+8);
		final String actualre=new SimpleDateFormat("yyyy/MM/dd HH:mm").format(actual);
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("xxx.xxxdate", n);
		super.fillInputBoxIn("xxx.xxxmoment", actualre);
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