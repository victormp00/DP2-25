package acme.testing.authenticated.shout;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;


public class AuthenticatedShoutListTest extends DP2Test{


@ParameterizedTest
@CsvFileSource(resources = "/authenticated/shout/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(10)
public void authenticatedListPositive (final int recordIndex, final String moment, final String author, 
	final String text) {
	super.navigateHome();
	super.signIn("normal1", "normal1");
	super.clickOnMenu("Authenticated", "Shouts list");
	

	super.checkColumnHasValue(recordIndex, 0, moment);
	super.checkColumnHasValue(recordIndex, 1, author);
	super.checkColumnHasValue(recordIndex, 2, text);
	super.signOut();
	
	}

@ParameterizedTest
@CsvFileSource(resources = "/authenticated/shout/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
@Order(20)
public void authenticatedListPositiveManager (final int recordIndex, final String moment, final String author, 
	final String text) {
	super.navigateHome();
	super.signIn("manager1", "manager1");
	super.clickOnMenu("Authenticated", "Shouts list");

	super.checkColumnHasValue(recordIndex, 0, moment);
	super.checkColumnHasValue(recordIndex, 1, author);
	super.checkColumnHasValue(recordIndex, 2, text);
	super.signOut();
	
	}
}