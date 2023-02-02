package org.farmaciaGen.farmacia.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // define que será uma tabela
@Table(name = "tb_produtos") // Define o nome da tabela
public class Farmacia {
	
	@Id // Define que é uma classe primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Funciona como o auto_increment
	private Long id;
	
	@NotNull // Funciona para não deixar o campo em branco
	@Size(min = 5, max = 100) // define a quantidade de caracteres
	public String nomeRemedio;
	
	@NotNull // Funciona para não deixar o campo em branco
	@Size(min = 10, max = 500) // define a quantidade de caracteres
	public String descricaoRemedio;
	
	@NotNull // não deixa campo em branco
	public float precoRemedio;
	
	@UpdateTimestamp
	private LocalDate data;
	
	@ManyToOne // Relacionamento de muitos para um.
	@JsonIgnoreProperties("farmacia") // impede o Looping infinito
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}

	public String getDescricaoRemedio() {
		return descricaoRemedio;
	}

	public void setDescricaoRemedio(String descricaoRemedio) {
		this.descricaoRemedio = descricaoRemedio;
	}

	public float getPrecoRemedio() {
		return precoRemedio;
	}

	public void setPrecoRemedio(float precoRemedio) {
		this.precoRemedio = precoRemedio;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
