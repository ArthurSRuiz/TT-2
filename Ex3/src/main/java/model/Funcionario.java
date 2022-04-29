package model;

public class Funcionario {
	public Funcionario() {
		this.id = -1;
		this.nome = "";
		this.cpf = "";
		this.rg = "";
		this.biografia = "";
	}

	public Funcionario(int id, String nome, String biografia, String rg, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.biografia = biografia;
	}



	private int id;
	private String nome, cpf, rg, biografia;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws RuntimeException {
			this.nome = nome;
	}

	public String getBio() {
		return biografia;
	}

	public void setBio(String biografia) throws RuntimeException {
			this.biografia = biografia;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "funcionario [id=" + id + ", nome=" + nome + ", bio=" + biografia + ", rg=" + rg + ", cpf="
				+ cpf + "]";
	}


}