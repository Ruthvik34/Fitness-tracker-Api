package com.project.fitnesstracker.Models;

import lombok.Data;

@Data
public class Address{
	private String zipcode;
	private Geo geo;
	private String suite;
	private String city;
	private String street;
}