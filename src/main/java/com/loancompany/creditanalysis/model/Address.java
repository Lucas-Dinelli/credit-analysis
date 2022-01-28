package com.loancompany.creditanalysis.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String publicArea;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = false)
	private String complement;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AddressType addressType;
	
	@ManyToMany(mappedBy = "addresses")
	private List<Client> clients;

}
