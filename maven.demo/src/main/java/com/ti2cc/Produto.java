package com.ti2cc;

public class Produto {
	private int codigo;
	private String tipo;
	private int quantidade;
	
	public Produto() {
		this.codigo = -1;
		this.tipo = "";
		this.quantidade = 0;
	}
	
	public Produto(int codigo, String produto, int quantidade) {
		this.codigo = codigo;
		this.tipo = produto;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setLogin(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", produto=" + tipo + ", quantidade=" + quantidade + "]";
	}	
}
