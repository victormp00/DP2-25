
package admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class SpamListCreateTest extends DP2Test {

	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/create-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createSpamNegative(final int recordIndex, final String es, final String en) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create Spam");
		super.fillInputBoxIn("spamEn", en);
		super.fillInputBoxIn("spamEs", es);
		super.clickOnSubmitButton("create");

		super.checkErrorsExist();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/create-spam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createSpamPositive(final int recordIndex, final String es, final String en) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create Spam");
		super.fillInputBoxIn("spamEn", en);
		super.fillInputBoxIn("spamEs", es);
		super.clickOnSubmitButton("create");
		this.signOut();

	}

}
