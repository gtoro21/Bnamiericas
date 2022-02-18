package com.bnamericas.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.bnamericas.model.Data;

import com.bnamericas.dao.BnAmericasDao;

import com.bnamericas.repository.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BnAmericasController {
	
	@Value("${backend}")
	private String backend;
	
	@Autowired
	BnAmericasRepository BnAmericasRepository;

	@ApiOperation(value = "Delete employee")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success" ),
		@ApiResponse(code = 204, message = "Not Content", response = ResponseEntity.class),
		@ApiResponse(code = 400, message = "Bad request", response = ResponseEntity.class) })
	@GetMapping("/save")
	public String createEmployee() {	
		BnAmericasDao.validate(backend,BnAmericasRepository);
		
	
		return "proceso ok";
	}
	

}
