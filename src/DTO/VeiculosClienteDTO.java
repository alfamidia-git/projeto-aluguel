package DTO;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Veiculo;

public class VeiculosClienteDTO {
	private Cliente cliente;
	private List<Veiculo> veiculos;
	private Date data;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
