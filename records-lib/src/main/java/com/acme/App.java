package com.acme;

import java.util.List;

import com.acme.dto.Customer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		List<Customer> customers = CustomerService.loadCustomers();

		// sorted by gender (females before males) then by last name ascending
		System.out.println("Sorted by gender (females before males) then by last name ascending:");
		customers.sort((Customer c1, Customer c2) -> {
			int cmp = c1.getGender().compareTo(c2.getGender());
			if (cmp == 0)
				cmp = c1.getLastName().compareTo(c2.getLastName());
			return cmp;
		});
		customers.forEach((customer) -> System.out.println(customer));

		// sorted by birth date, ascending
		System.out.println("Sorted by birth date, ascending:");
		customers.sort((Customer c1, Customer c2) -> c1.getDateOfBirth().compareTo(c2.getDateOfBirth()));
		customers.forEach((customer) -> System.out.println(customer));

		// sorted by last name, descending
		System.out.println("Sorted by last name, descending:");
		customers.sort((Customer c1, Customer c2) -> -1 * c1.getLastName().compareTo(c2.getLastName()));
		customers.forEach((customer) -> System.out.println(customer));
	}
}
