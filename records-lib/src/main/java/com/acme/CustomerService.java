/**
 * 
 */
package com.acme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import com.acme.dto.Customer;

/**
 * @author gstarke
 *
 */
public final class CustomerService {

	/**
	 * Returns a list of {@link Customer} retrieved from a set of files.
	 * 
	 * @return {@link List}&lt;{@link Customer}&gt;
	 */
	public static List<Customer> loadCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		List<String> files = loadFiles();

		if (files != null && !files.isEmpty()) {
			for (String file : files) {
				BufferedReader reader = null;
				try {
					String line = null;
					reader = new BufferedReader(
							new InputStreamReader(CustomerService.class.getClassLoader().getResourceAsStream(file)));

					// read line by line
					while ((line = reader.readLine()) != null) {
						Customer c = csvToCustomer(line);
						customers.add(c);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (reader != null)
							reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return customers;
	}

	/**
	 * Returns a {@link Customer} instance for the given string input.
	 * @param line the line
	 * @return {@link Customer}
	 */
	private static Customer csvToCustomer(String line) {
		if (line == null || line.length() <= 0)
			return null;

		String delims = ",| ";
		StringTokenizer tok = new StringTokenizer(line, delims);

		if (tok.countTokens() != 5)
			return null;

		int i = 0;
		Customer c = new Customer();
		while (tok.hasMoreTokens()) {
			if (i == 0)
				c.setLastName(tok.nextToken());
			else if (i == 1)
				c.setFirstName(tok.nextToken());
			else if (i == 2)
				c.setGender(tok.nextToken());
			else if (i == 3)
				c.setFavoriteColor(tok.nextToken());
			else if (i == 4)
				c.setDateOfBirth(tok.nextToken());
			i++;
		}

		return c;
	}

	/**
	 * Returns the list of files to load.
	 * @return {@link List}&lt;{@link String}&gt;
	 */
	private static List<String> loadFiles() {
		return Arrays.asList("comma.txt", "pipe.txt", "space.txt");
	}

}
