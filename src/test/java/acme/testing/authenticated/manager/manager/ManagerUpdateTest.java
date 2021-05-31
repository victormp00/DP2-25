package acme.testing.authenticated.manager.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ManagerUpdateTest extends DP2Test{
//	  En este test se comprueba la actualización de un mánager desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si no se actualiza, dado que el test intenta probar las diferentes validaciones.
//	  Las validaciones son:
//		-Ningún campo debe estar en blanco
//		-Ningún campo debe dar error por spam
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/manager/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateManagerNegative (final int recordIndex, final String company, final String sector) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Account", "Manager data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		

	}
//	  En este test comprueba la actualización de un mánager desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si se actualiza bien, y el test debería dar éxito.
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/manager/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateManagerPositive (final int recordIndex, final String company, final String sector) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Account", "Manager data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company",company);
		super.checkInputBoxHasValue("sector",sector);
		
		super.signOut();
		

	}
	
}
