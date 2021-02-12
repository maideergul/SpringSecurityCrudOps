package com.works.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	
	@NotNull(message = "name @NotNull")
	@NotEmpty(message = "name @NotEmpty")
	@Size(min = 10, max = 50, message = "name is min = 10 , max= 50")
	private String name;
	
	@Column(length = 200, unique = true)
	@NotNull(message = "mail @NotNull")
	@NotEmpty(message = "mail @NotEmpty")
	@Email(message = "mail format not valid")
	private String mail;
	
	@NotNull(message = "pass @NotNull")
	@NotEmpty(message = "pass @NotEmpty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "lower case, upper case, numeric, special character, min 8 char")
	private String pass;
	
}