/**
 * 
 */
package com.acme.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.acme.CustomerService;
import com.acme.dto.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author gstarke
 *
 */
@Path("/")
public class RecordService {

	/**
	 * local instance of list of customers
	 */
	private static final List<Customer> CUSTOMERS = CustomerService.loadCustomers();

	/**
	 * Stores a single user record in all of the formats (comma, pipe, and
	 * space), and then returns all records that may have been been added with
	 * multiple POSTs.
	 * 
	 * @param is
	 *            the input stream
	 * @return {@link Response}
	 */
	@Path("/")
	@POST
	public Response post(InputStream is) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = in.readLine()) != null) {
				Customer c = CustomerService.csvToCustomer(line);
				CUSTOMERS.add(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		return Response.status(200).entity(gson.toJson(CUSTOMERS, listOfCustomer)).build();
	}

	/**
	 * Returns customers sorted by gender (females before males) then by last
	 * name ascending.
	 * 
	 * @return {@link Response}
	 */
	@Path("gender")
	@GET
	@Produces("application/json")
	public Response gender() {
		List<Customer> customers = new ArrayList<Customer>(CUSTOMERS.size());
		customers.addAll(CUSTOMERS);

		customers.sort((Customer c1, Customer c2) -> {
			int cmp = c1.getGender().compareTo(c2.getGender());
			if (cmp == 0)
				cmp = c1.getLastName().compareTo(c2.getLastName());
			return cmp;
		});

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		return Response.status(200).entity(gson.toJson(customers, listOfCustomer)).build();
	}

	/**
	 * Returns customers sorted by birth date, ascending.
	 * 
	 * @return {@link Response}
	 */
	@Path("birthdate")
	@GET
	@Produces("application/json")
	public Response birthdate() {
		List<Customer> customers = new ArrayList<Customer>(CUSTOMERS.size());
		customers.addAll(CUSTOMERS);

		customers.sort((Customer c1, Customer c2) -> c1.getDateOfBirth().compareTo(c2.getDateOfBirth()));

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		return Response.status(200).entity(gson.toJson(customers, listOfCustomer)).build();
	}

	/**
	 * Returns customers sorted by last name, descending.
	 * 
	 * @return {@link Response}
	 */
	@Path("name")
	@GET
	@Produces("application/json")
	public Response name() {
		List<Customer> customers = new ArrayList<Customer>(CUSTOMERS.size());
		customers.addAll(CUSTOMERS);

		customers.sort((Customer c1, Customer c2) -> -1 * c1.getLastName().compareTo(c2.getLastName()));

		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		Type listOfCustomer = new TypeToken<List<Customer>>() {}.getType();
		return Response.status(200).entity(gson.toJson(customers, listOfCustomer)).build();
	}
}
