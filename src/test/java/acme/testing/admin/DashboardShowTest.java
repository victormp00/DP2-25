
package acme.testing.admin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.DP2Test;

public class DashboardShowTest extends DP2Test {

	@Test
	@Order(20)
	public void showDashboardPositive() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		this.signOut();
	}
}
