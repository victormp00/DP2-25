package acme.testing.authenticated.provider;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class AuthenticatedProviderCreateTest extends DP2Test {
//	  En este test se comprueba la creación de un provider desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si no se crea, dado que el test intenta probar las diferentes validaciones.
//	  Las validaciones son:
//		-Ningún campo debe estar en blanco
//		-Ningún campo debe dar error por spam
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/create-provider-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createProviderNegative(final int recordIndex, final String company, final String sector) {
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Account", "Become a provider");
		super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();

	}
//	  En este test se comprueba la creación de un provider desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si se crea bien, y el test debería dar éxito.
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/create-provider-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createProviderPositive(final int recordIndex, final String company, final String sector) {
		super.signIn("normal1", "normal1");
		super.clickOnMenu("Account", "Become a provider");
		super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");

		this.signOut();

	}
	
}
