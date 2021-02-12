package com.works.restcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.ERest;
import com.works.utils.R;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	
	final ProductRepository pRepo;
	public ProductRestController( ProductRepository pRepo ) {
		this.pRepo = pRepo;
	}
	
	@PostMapping("/insert")
	public Map<ERest, Object> insert( @Valid @RequestBody Product product ) {
		Map<ERest, Object> hm = new LinkedHashMap<>();
		
		try {
			Product pro = pRepo.saveAndFlush(product);
			hm.put(ERest.status, true);
			hm.put(ERest.message, R.i_success);
			hm.put(ERest.result, pro);
		} catch (Exception e) {
			hm.put(ERest.status, false);
			hm.put(ERest.message, R.i_fail);
			hm.put(ERest.result, product);
		}
		
		return hm;
	}	
	
	@GetMapping("/list")
	public Map<ERest, Object> list() {
		
		Map<ERest, Object> hm = new LinkedHashMap<>();
		
		hm.put(ERest.status, true);
		hm.put(ERest.message, R.list_success);
		hm.put(ERest.result, pRepo.findAll());
		
		return hm;
	}	
	
	@DeleteMapping("/deleteSingle")
	public Map<ERest, Object> deleteSingle( @RequestBody Product product ) {
		Map<ERest, Object> hm = new LinkedHashMap<>();
		try {
			pRepo.deleteById(product.getPid());
			hm.put(ERest.status, true);
			hm.put(ERest.message, R.d_success);
			hm.put(ERest.result, product.getPid());
		} catch (Exception e) {
			hm.put(ERest.status, false);
			hm.put(ERest.message, R.d_fail);
			hm.put(ERest.result, product.getPid());
		}
		return hm;
	}	
	
	@PutMapping("/updateSingle")
	public Map<ERest, Object> updateSingle( @Valid @RequestBody Product product ) {
		Map<ERest, Object> hm = new LinkedHashMap<>();
		
		hm.put(ERest.status, false);
		hm.put(ERest.message, R.u_fail);
		hm.put(ERest.result, product);
		
		try {
			Optional<Product> oPro = pRepo.findById(product.getPid());
			if ( oPro.isPresent() ) {
				Product pro = pRepo.saveAndFlush(product);
				hm.put(ERest.status, true);
				hm.put(ERest.message, R.u_success);
				hm.put(ERest.result, pro);
			}
		} catch (Exception e) {}
		
		return hm;
	}	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<ERest, Object> errorHandler( MethodArgumentNotValidException ex ) {
		Map<ERest, Object> hm = new LinkedHashMap<>();
		
		List<ObjectError> els = ex.getBindingResult().getAllErrors();
		List<Map<String, String>> ls = new ArrayList<>();
		for (ObjectError item : els) {
			String fieldName = (( FieldError ) item).getField();
			String fieldMessage = item.getDefaultMessage();
			Map<String, String> ehm = new HashMap<>();
			ehm.put("fieldName", fieldName);
			ehm.put("fieldMessage", fieldMessage);
			ls.add(ehm);
		}
		hm.put(ERest.status, false);
		hm.put(ERest.message, R.errors);
		hm.put(ERest.result, ls);
		return hm;
	}	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidFormatException.class)
	public Map<ERest, Object> errorHandler( InvalidFormatException ex ) {
		Map<ERest, Object> hm = new LinkedHashMap<>();
		
		hm.put(ERest.status, false);
		hm.put(ERest.message, ex.getMessage());
		
		return hm;
	} 	

}