package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ManagerTaskCreateTest extends DP2Test{
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTaskNegative (final int recordIndex, final String title, final String description,
		final String creation,final String finish,final String workload,final String link) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create a task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("creation", creation);
		super.fillInputBoxIn("finish", finish);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("publico", "true");
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		super.signOut();
		
		

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTaskPositive (final int recordIndex, final String title, final String description,
		final String creation,final String finish,final String workload,final String link) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create a task");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("creation", creation);
		super.fillInputBoxIn("finish", finish);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("publico", "true");
		super.clickOnSubmitButton("Create");
		
		super.clickOnLink("Manager tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
	//	super.checkColumnHasValue(recordIndex, 1, creation);
		super.checkColumnHasValue(recordIndex, 2, finish);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
	//	super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("finish", finish);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
		
		

	}
	
}
