package br.entrega.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_entrega", catalog = "entrega", schema = "dbo")
public class Sla implements Serializable {

	public Sla() {
		super();	
	}

	public Sla(String uf, Integer sla) {
		super();		
		this.uf = uf;
		this.sla = sla;
	}

	private static final long serialVersionUID = -6829002016262748507L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	private String uf;
	
	private Integer sla;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}
	
}
