package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class SpamDeleteTest  extends DP2Test {
//	Este test comprueba que las palabras de spam se borran correctamente desde la perspectiva de manager
//	Se considera exitoso si se borra correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/admin/spam/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTask(final int recordIndex) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam word list");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("delete");

	}

}
