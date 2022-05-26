package service;

import java.util.List;
import java.util.Scanner;

import exceptions.VeiculoException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;
import repository.RepositoryImpl;
import util.Contador;

public class VeiculoService {
	
	private RepositoryImpl<Integer, Veiculo> repository = new RepositoryImpl<>();
	private Scanner sc;
	
	public VeiculoService(Scanner sc) {
		this.sc = sc;
		Veiculo veiculo1 = new Veiculo("IPO2525", "HB20", "Hyndai", "Preto", 200000.0, Tipo.CARRO);
		Veiculo veiculo2 = new Veiculo("IPO2526", "I30", "Hyndai", "Preto", 200000.0, Tipo.CARRO);
		Veiculo veiculo3 = new Veiculo("IPO2527", "A3", "Audi", "Preto", 200000.0, Tipo.CARRO);
		Veiculo veiculo4 = new Veiculo("IPO2528", "CG", "Honda", "Preto", 200000.0, Tipo.CARRO);
		repository.salvar(veiculo1.getId(), veiculo1);
		repository.salvar(veiculo2.getId(), veiculo2);
		repository.salvar(veiculo3.getId(), veiculo3);
		repository.salvar(veiculo4.getId(), veiculo4);
	}
	public void cadastrarVeiculo() {
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
		
		repository.salvar(veiculo.getId(), veiculo);
	}
	
	public void mostrarTodos() {
		for(Veiculo veiculo : repository.buscarTodos()) {
			System.out.println(veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor() + 
					", R$" + veiculo.getValor());
		}
		
	}
	
	public void mostrarTodosLivres() {
		for(Veiculo veiculo : repository.buscarTodos()) {
			if(veiculo.getStatus() == Status.LIVRE) {
				System.out.println(veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor() + 
						", R$" + veiculo.getValor());
			}
		}
	}
	
	public Veiculo buscarPorId(Integer id) {
		Veiculo veiculo = this.repository.buscaPorId(id);
		
		if(veiculo == null) {
			throw new VeiculoException("Veículo não encontrado. ID: " + id);
		}
		
		return veiculo;
	}
	
	public void atualizarVeiculo(Veiculo veiculo) {
		this.repository.salvar(veiculo.getId(), veiculo);
	}
	public void buscarVeiculosAlugados() {
		List<Veiculo> veiculos = this.repository.buscarTodos();	
		
		for(Veiculo veiculo : veiculos) {
			if(veiculo.getStatus() == Status.ALUGADO) {
				System.out.println("Cliente " + veiculo.getCliente().getNome() + " - " +  veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor());
			}
		}
	}
}
	
	
