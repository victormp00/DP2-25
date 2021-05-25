package acme.testing.authenticated.consumer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ConsumerCreateTest extends DP2Test{
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createConsumerNegative (final int recordIndex, final String company, final String sector) {
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Account", "Become a consumer");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");
		
		super.checkErrorsExist();
		

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createConsumerPositive (final int recordIndex, final String company, final String sector) {
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Account", "Become a consumer");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");
		
		super.clickOnMenu("Account", "Consumer data");
		super.checkInputBoxHasValue("company",company);
		super.checkInputBoxHasValue("sector",sector);
		this.signOut();

	}
	

}
