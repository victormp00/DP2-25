package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ThresholdUpdateTest extends DP2Test{
//	  En este test se comprueba la actualización del threshold desde la perspectiva de un administrador
//	  Se considera exitoso si no se crea, dado que el test intenta probar las diferentes validaciones.
//	  Las validaciones son:
//		-El threshold debe estar en blanco
//		-El threshold debe estar en 0 y 100
	@ParameterizedTest
	@CsvFileSource(resources = "/admin/threshold/update-threshold-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateThresholdNegative (final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show threshold");
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("update");
		
		super.checkErrorsExist();
		
	}
//	  En este test se comprueba la actualización de un threshold desde la perspectiva de un administrador
//	  Se considera exitoso si se actualiza bien, y el test debería dar éxito.
	@ParameterizedTest
	@CsvFileSource(resources = "/admin/threshold/update-threshold-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateThresholdPositive (final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Show threshold");
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("update");
		
		super.clickOnMenu("Administrator", "Show threshold");
		super.checkInputBoxHasValue("threshold",threshold);
		
		super.signOut();
		

	}

}
