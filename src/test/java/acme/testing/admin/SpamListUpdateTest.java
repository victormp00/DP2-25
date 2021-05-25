
package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class SpamListUpdateTest extends DP2Test {

	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/update-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateSpamNegative(final int recordIndex, final String es, final String en) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam word list");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("spamEn", en);
		super.fillInputBoxIn("spamEs", es);
		super.clickOnSubmitButton("update");

		super.checkErrorsExist();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/update-spam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateSpamPositive(final int recordIndex, final String es, final String en) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam word list");
		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("spamEn", en);
		super.fillInputBoxIn("spamEs", es);
		super.clickOnSubmitButton("update");
		this.signOut();

	}

}
