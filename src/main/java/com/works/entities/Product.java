package com.works.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Min(value = 1, message = "pid Min value = 1")
	private int pid;
	
	@NotNull(message = "title Not Null")
	@NotEmpty(message = "title Not Empty")
	@Size(min = 3, max = 30, message = "title min = 3, max = 30")
	@Column(unique = true)
	private String title;
	
	private String detail;
	
	@Min(value = 10, message = "price @Min 10")
	@Max(value = 10000, message = "price @Max 10000")
	private int price;
	
}