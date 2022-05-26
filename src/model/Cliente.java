package model;

import java.util.ArrayList;
import java.util.List;

import util.Contador;

public class Cliente extends Pessoa{
	
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public Cliente(String nome, String cpf, String senha) {
		super(Contador.proximoId(), nome, cpf, senha);
		
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	

}
