package model;

import java.time.LocalDate;

import util.Contador;

public class Aluguel {
		private Integer id;
		private Veiculo veiculo;
		private Cliente cliente;
		private Vendedor vendedor;
		private LocalDate data;
		
		public Aluguel(Veiculo veiculo, Cliente cliente, Vendedor vendedor, LocalDate data) {
			this.id = Contador.proximoId();
			this.veiculo = veiculo;
			this.cliente = cliente;
			this.vendedor = vendedor;
			this.data = data;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Veiculo getVeiculo() {
			return veiculo;
		}

		public void setVeiculo(Veiculo veiculo) {
			this.veiculo = veiculo;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Vendedor getVendedor() {
			return vendedor;
		}

		public void setVendedor(Vendedor vendedor) {
			this.vendedor = vendedor;
		}

		public LocalDate getData() {
			return data;
		}

		public void setData(LocalDate data) {
			this.data = data;
		}
		
			
		
}
