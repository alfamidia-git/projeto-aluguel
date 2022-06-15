package service;

import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import model.Aluguel;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import repository.AluguelRepository;
import repository.RepositoryImpl;

public class AluguelService {

	private Scanner sc;
	private AluguelRepository repository = new AluguelRepository();
	
	public AluguelService(Scanner sc) {
		this.sc = sc;
	}
	
	
	public void salvar(Cliente cliente, Vendedor vendedor, Veiculo veiculo) throws SQLException {
		Aluguel aluguel = new Aluguel(veiculo.getId(), cliente.getId(), vendedor.getId());		
		repository.salvar(aluguel);
	}
	
	public void excluirAlguelPorVeiculoID(Integer id) throws SQLException {
		this.repository.excluirAluguelPorVeiculoID(id);
	}


//	public void mostrarTotalVendas() {
//		List<Aluguel> alugueis = this.repository.buscarTodos();
//		
//		Double totalVendas = 0.0;
//		
//		for(Aluguel aluguel : alugueis) {
//			totalVendas += aluguel.getVeiculo().getValor();
//		}
//		
//		System.out.println("O total de vendas é: " + totalVendas);
//	}
}
