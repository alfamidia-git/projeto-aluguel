package service;

import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

import model.Aluguel;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import repository.RepositoryImpl;

public class AluguelService {

	private Scanner sc;
	private RepositoryImpl<Integer, Aluguel> repository = new RepositoryImpl<>();
	
	public AluguelService(Scanner sc) {
		this.sc = sc;
	}
	
	
	public void salvar(Cliente cliente, Vendedor vendedor, Veiculo veiculo) {
		Aluguel aluguel = new Aluguel(veiculo, cliente, vendedor, LocalDate.now());		
		repository.salvar(aluguel.getId(), aluguel);
	}


	public void mostrarTotalVendas() {
		List<Aluguel> alugueis = this.repository.buscarTodos();
		
		Double totalVendas = 0.0;
		
		for(Aluguel aluguel : alugueis) {
			totalVendas += aluguel.getVeiculo().getValor();
		}
		
		System.out.println("O total de vendas é: " + totalVendas);
	}
}
