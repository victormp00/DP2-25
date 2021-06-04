package acme.testing.authenticated.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.DP2Test;

public class ManagerTaskCreateTest extends DP2Test{
//	  En este test se comprueba la creación de una task desde la perspectiva de un usuario autenticado como manager
//	  Se considera exitoso si no se crea, dado que el test intenta probar las diferentes validaciones.
//	  Las validaciones son:
//		-El título no debe estar en blanco y debe tener una longitud de entre 5 y 60.
//		-La descripción no debe estar vacía y debe estar entre 1 y 500 caracteres.
//		-Ni creation ni finish deben estar vacíos y creation no puede ir después de finish.
//		-Ni el título, ni el link, ni la descripción deben dar error por spam.
//		-El workload encajar entre la fecha de fin y la de inicio.
//		-Los dígitos del workload no deben ser mayores a 60.
		
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
//	  En este test se comprueba la creación de una tarea desde la perspectiva de un usuario autenticado como mánager
//	  Se considera exitoso si se crea bien, y el test debería dar éxito.
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
		super.checkColumnHasValue(recordIndex, 2, finish);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("finish", finish);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
		
		

	}
	
}
