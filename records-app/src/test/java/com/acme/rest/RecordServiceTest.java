/**
 * 
 */
package com.acme.rest;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.acme.dto.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author gstarke
 *
 */
public class RecordServiceTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public RecordServiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RecordServiceTest.class);
	}

	/**
	 * Test method birthdate of the {@link RecordService} service.
	 */
	public void testBirthdate() {
		RecordService service = new RecordService();
		Response resp = service.birthdate();
		assertTrue(resp.getStatus() == Status.OK.getStatusCode());

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		List<Customer> customers = gson.fromJson(resp.getEntity().toString(), listOfCustomer);

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

	/**
	 * Test method gender of the {@link RecordService} service.
	 */
	public void testGender() {
		RecordService service = new RecordService();
		Response resp = service.gender();
		assertTrue(resp.getStatus() == Status.OK.getStatusCode());

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		List<Customer> customers = gson.fromJson(resp.getEntity().toString(), listOfCustomer);

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
	
	/**
	 * Test method name of the {@link RecordService} service.
	 */
	public void testName() {
		RecordService service = new RecordService();
		Response resp = service.name();
		assertTrue(resp.getStatus() == Status.OK.getStatusCode());

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		List<Customer> customers = gson.fromJson(resp.getEntity().toString(), listOfCustomer);

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