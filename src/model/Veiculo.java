package model;

import util.Contador;

public class Veiculo {
	
	public enum Status{
		ALUGADO,
		LIVRE
	}
	
	public enum Tipo{
		CARRO,
		MOTO,
		CAMINHAO
	}
	
	private Integer id;
	private String placa;
	private String modelo;
	private String marca;
	private String cor;
	private Double valor;
	private Status status;
	private Tipo tipo;
	private Cliente cliente;
	
	
	public Veiculo(String placa, String modelo, String marca, String cor, Double valor, Tipo tipo) {
		this.id = Contador.proximoId();
		this.status = Status.LIVRE;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", cor=" + cor
				+ ", valor=" + valor + ", status=" + status + ", tipo=" + tipo + "]";
	}
	
}
