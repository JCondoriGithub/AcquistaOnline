package com.acquistionline.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clients_table")
public class Client {

	@Id
	@Column(name = "client_code", nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name, surname;

	private String email;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Order> orders;
}
