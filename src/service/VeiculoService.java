package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import exceptions.VeiculoException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;
import repository.RepositoryImpl;
import repository.VeiculoRepository;
import util.Contador;

public class VeiculoService {
	
	private VeiculoRepository repository = new VeiculoRepository();
	private Scanner sc;
	
	public VeiculoService(Scanner sc) {
		this.sc = sc;
	}
	public void cadastrarVeiculo() throws SQLException {
		sc.nextLine();
		
		System.out.println("Digite o modelo do veículo");
		String modelo = sc.nextLine();
		System.out.println("Digite o placa do veículo");
		String placa = sc.nextLine();
		System.out.println("Digite o marca do veículo");
		String marca = sc.nextLine();
		System.out.println("Digite a cor do veículo");
		String cor = sc.nextLine();
		System.out.println("Digite o valor do veículo");
		Double valor = sc.nextDouble();
		System.out.println("Escolha a opção para o veículo: ");
		System.out.println("1 - CARRO");
		System.out.println("2 - MOTO");
		System.out.println("3 - CAMINHAO");
		int opcao = sc.nextInt();
		Tipo tipo = null;
		switch(opcao) {
		case 1 : tipo = Tipo.CARRO; break;
		case 2 : tipo = Tipo.MOTO; break;
		case 3 : tipo = Tipo.CAMINHAO; break;
		}
		
		Veiculo veiculo = new Veiculo(placa, modelo, marca, cor, valor, tipo);
		
		repository.salvar(veiculo);
	}
	
	public void mostrarTodos() throws SQLException {
		
		List<Veiculo> veiculos = repository.buscarTodos();
		
		veiculos.forEach(v -> System.out.println(v));
		
	}
	
	public void mostrarTodosLivres() throws SQLException {
//		for(Veiculo veiculo : repository.buscarTodos()) {
//			if(veiculo.getStatus() == Status.LIVRE) {
//				System.out.println(veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor() + 
//						", R$" + veiculo.getValor());
//			}
//		}
		
		List<Veiculo> veiculos = repository.buscarTodos();
		
		
		veiculos.stream().filter(v -> v.getStatus() == Status.LIVRE)
						 .forEach(v -> System.out.println(v));
	}
	
	public Veiculo buscarPorId(Integer id) throws SQLException {
		Veiculo veiculo = this.repository.buscarPorID(id).get(0);
		
		if(veiculo == null) {
			throw new VeiculoException("Veículo não encontrado. ID: " + id);
		}
		
		return veiculo;
	}
	
	public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
		this.repository.atualizar(veiculo);
	}
	public void buscarVeiculosAlugados() throws SQLException {
		List<Veiculo> veiculos = this.repository.buscarTodos();	
		
//		for(Veiculo veiculo : veiculos) {
//			if(veiculo.getStatus() == Status.ALUGADO) {
//				System.out.println("Cliente " + veiculo.getCliente().getNome() + " - " +  veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor());
//			}
//		}
		
		veiculos.stream().filter(v -> v.getStatus() == Status.ALUGADO)
		.forEach(v -> System.out.println(v));
	}
}
	
	
