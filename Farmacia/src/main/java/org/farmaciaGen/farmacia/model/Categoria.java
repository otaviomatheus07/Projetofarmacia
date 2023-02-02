package org.farmaciaGen.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //define que sera uma tabela
@Table (name = "tb_categoria") // Define o nome da tabela
public class Categoria {
	@Id // Define Que é uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O Atributo Descrição é obrigatório")
	private String descricao;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)// Relacionamento de um para muitos // MAPPEDBY: Define o lado proprietario do relacinamento
	// Cascade: Faz com que qualquer funcao feita, seja aplicada todas as entidades. // CascadeType.REMOVE: Tudo é removido quando algo é apagado.
	@JsonIgnoreProperties("categoria") //impede o looping infinito
	private List<Farmacia> farmacia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoP() {
		return descricao;
	}

	public void setDescricaoP(String descricao) {
		this.descricao = descricao;
	}

	public List<Farmacia> getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(List<Farmacia> farmacia) {
		this.farmacia = farmacia;
	}

}
