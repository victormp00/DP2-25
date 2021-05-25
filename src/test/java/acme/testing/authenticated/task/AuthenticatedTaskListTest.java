package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedTaskListTest extends DP2Test{

@ParameterizedTest
@CsvFileSource(resources = "/authenticated/task/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(10)
public void authenticatedListPositive (final int recordIndex, final String title, final String creation, 
	final String finish, final String workload, final String description,final String link) {
	super.signIn("normal1", "normal1");
	super.clickOnLink("Public tasks");

	super.checkColumnHasValue(recordIndex, 0, title);
	super.checkColumnHasValue(recordIndex, 1, creation);
	super.checkColumnHasValue(recordIndex, 2, finish);
	super.checkColumnHasValue(recordIndex, 3, workload);
	super.checkColumnHasValue(recordIndex, 4, description);
	super.checkColumnHasValue(recordIndex, 5, link);
	
	super.clickOnListingRecord(recordIndex);
	
	super.checkInputBoxHasValue("title", title);
	super.checkInputBoxHasValue("description", description);
	super.checkInputBoxHasValue("creation", creation);
	super.checkInputBoxHasValue("finish", finish);
	super.checkInputBoxHasValue("workload", workload);
	super.checkInputBoxHasValue("link", link);
	super.signOut();
	
	}

@ParameterizedTest
@CsvFileSource(resources = "/authenticated/task/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(20)
public void authenticatedListPositiveManager (final int recordIndex, final String title, final String creation, 
	final String finish, final String workload, final String description,final String link) {
	super.signIn("manager1", "manager1");
	super.clickOnLink("Public tasks");

	super.checkColumnHasValue(recordIndex, 0, title);
	super.checkColumnHasValue(recordIndex, 1, creation);
	super.checkColumnHasValue(recordIndex, 2, finish);
	super.checkColumnHasValue(recordIndex, 3, workload);
	super.checkColumnHasValue(recordIndex, 4, description);
	super.checkColumnHasValue(recordIndex, 5, link);
	
	super.clickOnListingRecord(recordIndex);
	
	super.checkInputBoxHasValue("title", title);
	super.checkInputBoxHasValue("description", description);
	super.checkInputBoxHasValue("creation", creation);
	super.checkInputBoxHasValue("finish", finish);
	super.checkInputBoxHasValue("workload", workload);
	super.checkInputBoxHasValue("link", link);
	super.signOut();
	
	}
}