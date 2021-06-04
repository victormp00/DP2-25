package acme.testing.authenticated.consumer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ConsumerUpdateTest extends DP2Test{
//	  En este test se comprueba la actualización de un consumer desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si no se actualiza, dado que el test intenta probar las diferentes validaciones.
//	  Las validaciones son:
//		-Ningún campo debe estar en blanco
//		-Ningún campo debe dar error por spam

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateConsumerNegative (final int recordIndex, final String company, final String sector) {
		super.signIn("consumer1", "consumer1");
		super.clickOnMenu("Account", "Consumer data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		

	}
//	  En este test se comprueba la actualización de un consumer desde la perspectiva de un usuario autenticado
//	  Se considera exitoso si se actualiza bien, y el test debería dar éxito.
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateConsumerPositive (final int recordIndex, final String company, final String sector) {
		super.signIn("consumer1", "consumer1");
		super.clickOnMenu("Account", "Consumer data");		
		super.fillInputBoxIn("company", company);
        super.fillInputBoxIn("sector", sector);
        super.clickOnSubmitButton("Update");

		this.signOut();

	}
	
}
