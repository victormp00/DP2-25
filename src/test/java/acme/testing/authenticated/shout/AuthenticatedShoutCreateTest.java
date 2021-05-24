package acme.testing.authenticated.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedShoutCreateTest extends DP2Test {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/shout/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Authenticated", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		
		
	
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/shout/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShout(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Authenticated", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/shout/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShoutManager(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Authenticated", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		
		
	
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/shout/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShoutManager(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Authenticated", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	
	
	
	
	
}