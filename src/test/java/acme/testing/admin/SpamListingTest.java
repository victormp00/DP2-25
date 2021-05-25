
package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class SpamListingTest extends DP2Test {

	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/list-all-spam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listingSpam(final int recordIndex, final String es, final String en) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam word list");
		
		super.checkColumnHasValue(recordIndex, 0, en);
		super.checkColumnHasValue(recordIndex, 1, es);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("spamEn", en);
		super.checkInputBoxHasValue("spamEs", es);
		

		super.signOut();

	}


}
