package model;

import java.time.LocalDate;

import util.Contador;

public class Aluguel {
		private Integer id;
		private Integer idVeiculo;
		private Integer idCliente;
		private Integer idVendedor;
		private LocalDate data;
		
		public Aluguel(Integer veiculo, Integer cliente, Integer vendedor) {
			this.id = Contador.proximoId();
			this.idVeiculo = veiculo;
			this.idCliente = cliente;
			this.idVendedor = vendedor;
			this.data = LocalDate.now();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public LocalDate getData() {
			return data;
		}

		public void setData(LocalDate data) {
			this.data = data;
		}

		public Integer getIdVeiculo() {
			return idVeiculo;
		}

		public void setIdVeiculo(Integer idVeiculo) {
			this.idVeiculo = idVeiculo;
		}

		public Integer getIdCliente() {
			return idCliente;
		}

		public void setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
		}

		public Integer getIdVendedor() {
			return idVendedor;
		}

		public void setIdVendedor(Integer idVendedor) {
			this.idVendedor = idVendedor;
		}
		
			
		
}
