package br.com.fiap.resource;

public abstract class Usuario {
	private String cnpj;
	private String email;
	private String senha;
	private Endereco endereco;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cpnj) {
		this.cnpj = cpnj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}	
	
}
