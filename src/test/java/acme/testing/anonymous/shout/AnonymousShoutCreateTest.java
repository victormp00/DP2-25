package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AnonymousShoutCreateTest extends DP2Test {

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/positiveShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		
		
	
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/negativeShout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateShout(final int recordIndex,final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	
	
	
	
	
	
	
}