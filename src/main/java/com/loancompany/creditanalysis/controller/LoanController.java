package com.loancompany.creditanalysis.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loancompany.creditanalysis.dto.LoanInputDTO;
import com.loancompany.creditanalysis.dto.LoanOutputDTO;
import com.loancompany.creditanalysis.dto.LoanSpecificOutputDTO;
import com.loancompany.creditanalysis.service.LoanService;

@RestController
@RequestMapping("/v1/loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping
	public ResponseEntity<LoanOutputDTO> create(@Valid @RequestBody LoanInputDTO loanInputDTO) {
		LoanOutputDTO loanOutputDTO = loanService.create(loanInputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(loanOutputDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(loanOutputDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<LoanOutputDTO>> listAll() {
		return ResponseEntity.ok().body(loanService.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanSpecificOutputDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(loanService.findById(id));
	}

}
