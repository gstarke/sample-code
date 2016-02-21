/**
 * 
 */
package com.acme;

import java.util.List;

import com.acme.dto.Customer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author gstarke
 *
 */
public class CustomerServiceTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CustomerServiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CustomerServiceTest.class);
	}

	/**
	 * Test the load method of the {@link CustomerService} service.
	 */
	public void testLoadCustomers() {
		List<Customer> customers = CustomerService.loadCustomers();

		assertNotNull(customers);
		assertTrue(customers.size() > 0);

		for (Customer c : customers) {
			assertNotNull(c.getFavoriteColor());
			assertNotNull(c.getFirstName());
			assertNotNull(c.getLastName());
			assertNotNull(c.getDateOfBirth());
			assertTrue(c.getDateOfBirth().getTime() > 0);
			assertFalse(c.getGender().equals(Customer.GENDER.U));
		}
	}

}
