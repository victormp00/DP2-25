
package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.DP2Test;
// //Se prueba el dashboard desde el punto de vista de un andministrador ,  se considera exitoso si se muestra el dashboard con exito, el test debe dar exito
public class DashboardShowTest extends DP2Test {

	@Test
	@Order(20)
	public void showDashboardPositive() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		this.signOut();
	}
}
