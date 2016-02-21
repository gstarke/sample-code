/**
 * 
 */
package com.acme.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author gstarke
 *
 */
public class Customer {

	// gender type
	public enum GENDER {
		F, M, U
	}

	/**
	 * lastName property
	 */
	private String lastName;

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * firstName property
	 */
	private String firstName;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * gender property
	 */
	private GENDER gender;

	/**
	 * @return the gender
	 */
	public GENDER getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(final GENDER gender) {
		this.gender = gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(final String gender) {
		try {
			this.gender = GENDER.valueOf(gender);
		} catch (IllegalArgumentException e) {
			this.gender = GENDER.U;
		}
	}

	/**
	 * favoriteColor property
	 */
	private String favoriteColor;

	/**
	 * @return the favoriteColor
	 */
	public String getFavoriteColor() {
		return favoriteColor;
	}

	/**
	 * @param favoriteColor
	 *            the favoriteColor to set
	 */
	public void setFavoriteColor(final String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	/**
	 * dateOfBirth property
	 */
	private Date dateOfBirth;

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final String dateOfBirth) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			this.dateOfBirth = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				this.dateOfBirth = sdf.parse("01/01/1970");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
		return gson.toJson(this);
	}

}
