package com.acquistionline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clients_table")
public class Client {

	@Id
	@Column(name = "client_code", nullable = false)
	private String clientCode;
	
	@Column(nullable = false)
	private String name, surname;

	private String email;
}
