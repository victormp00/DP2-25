package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ThresholdUpdateTest extends DP2Test{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/admin/threshold/update-threshold-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateThresholdNegative (final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show threshold");
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("update");
		
		super.checkErrorsExist();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/admin/threshold/update-threshold-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateThresholdPositive (final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show threshold");
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("update");
		
		super.clickOnMenu("Administrator", "Show threshold");
		super.checkInputBoxHasValue("threshold",threshold);
		
		super.signOut();
		

	}

}
