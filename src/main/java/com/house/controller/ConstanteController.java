package com.house.controller;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.constante.StaticListOfValues;
import com.house.helper.MessageHelper;
import com.house.helper.ResponseHelper;
 

@RestController
@RequestMapping("/constantes_param")
@CrossOrigin(origins = "*")
public class ConstanteController {
    
    private StaticListOfValues data = new StaticListOfValues();

	@GetMapping("/list_trimestres") 
	public ResponseEntity<Object> getAllParametreDivers(@RequestHeader(name = "Accept-Language", required = false) String localeString) {
		
		if (!data.getTypeTrimestres().isEmpty()) {
			return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data.getTypeTrimestres(), true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.OK);
		}

	}

	@GetMapping("/list_roles") 
	public ResponseEntity<Object> getRole(@RequestHeader(name = "Accept-Language", required = false) String localeString) {
		
		if (!data.getRoles().isEmpty()) {
			return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), data.getRoles(), true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHelper(MessageHelper.success(new Locale(localeString)), false), HttpStatus.OK);
		}

	}
}
