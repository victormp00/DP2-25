
package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class SpamCreateTest extends DP2Test {
	//Se prueba la creacion de una palabra de Spam desde el punto de vista de un administrador,se prueban los paramtros : spamEn y spamEs se considera exitoso si se crea la palabra de spam con exito, el test debe dar exito
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
	//Se prueba la creacion de una palabra de Spam desde el punto de vista de un administrador,se prueban los paramtros : spamEn que no puede estar en blanco y spamEs que no puede estar en blanco se considera exitoso si no se crea la palabra de spam con exito, el test debe dar exito
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
