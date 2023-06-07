package com.acquistionline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "orders_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_code", nullable = false)
	private int operationCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_code", nullable = false)
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_product", nullable = false)
	private Product product;
	
	@Column(name = "payment_type", nullable = false)
	private String paymentType;
	
	@Column(name = "qty_product", nullable = false)
	private int qtyProduct;
}
