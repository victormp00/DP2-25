package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedTaskListAnonTest extends DP2Test{
//	En este se prueban los tests de tasks correspondientes a la lista de tasks anónimas desde la perspectiva de un ususario autenticado
//	los resultados para este test deben ser positivos dado que prueban si la lista de tasks que están en /anonymous/task/list-positive.csv
//	se cumplen y así ocurre.


	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void authenticatedListingAnon (final int recordIndex, final String title, final String creation, 
		final String finish, final String workload, final String description,final String link) {
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Anonymous", "Anonymous Tasks");

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
		super.clickOnReturnButton("return");

	}
}