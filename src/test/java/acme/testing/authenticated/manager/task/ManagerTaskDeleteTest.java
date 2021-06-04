package acme.testing.authenticated.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ManagerTaskDeleteTest extends DP2Test{
//	Este test comprueba que las tareas se borran correctamente desde la perspectiva de manager
//	Se considera exitoso si se borra correctamente
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTask(final int recordIndex) {
		super.signIn("manager1", "manager1");
		super.clickOnLink("Manager tasks");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete");

	}
	
}
