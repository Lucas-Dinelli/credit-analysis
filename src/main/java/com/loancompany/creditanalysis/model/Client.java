package com.loancompany.creditanalysis.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(nullable = false, unique = true)
	private String rg;
	
	@Column(nullable = false)
	private BigDecimal income;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany
	@JoinTable(
			name = "clients_addresses",
			joinColumns = @JoinColumn(name="client_id"),
			inverseJoinColumns = @JoinColumn(name="address_id")
	)
	@ToString.Exclude
	private List<Address> addresses;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Loan> loans;
	
}
