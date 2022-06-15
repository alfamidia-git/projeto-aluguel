package service;

import java.sql.SQLException;
import java.util.List;

import java.util.Scanner;

import exceptions.ClienteException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;
import repository.ClienteRepository;
import repository.RepositoryImpl;

import java.util.stream.*;

import DTO.VeiculosClienteDTO;

public class ClienteService {
	private ClienteRepository repository = new ClienteRepository();
	Scanner sc;
	
	public ClienteService(Scanner sc) {
		this.sc = sc;
	}
	
	public Cliente cadastrarCliente() throws ClienteException, SQLException {
		
		System.out.println("Digite o nome do cliente");
		String nome = sc.nextLine();
		System.out.println("Digite o cpf do cliente");
		String cpf = sc.nextLine();
		System.out.println("Digite a senha do cliente");
		String senha = sc.nextLine();
	
		List<Cliente> clientes = this.repository.buscarTodos();
		
		for(Cliente cliente : clientes) {
			if(cliente.getCpf().equals(cpf)) {
				throw new ClienteException("Cliente já cadastrado!!");
			}
		}
		
		Cliente cliente = new Cliente(nome, cpf, senha);
		
		repository.salvar(cliente);
		
		cliente = this.repository.buscarTodos().get(clientes.size());
		
		return cliente;
	}
	
	
	public Cliente buscarPeloCpf(String cpf) throws ClienteException, SQLException {
		List<Cliente> clientes = repository.buscarTodos();
		Cliente cl = null;
//		for(Cliente cliente : clientes) {
//			if(cliente.getCpf().equals(cpf)) {
//				cl = cliente;
//			}
//		}
		
		cl = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpf))
				.findFirst().orElse(null);
		
		if(cl == null) {
			throw new ClienteException("Cliente não encontrado!!");				
		}
		return cl;
	}
	
	public Cliente tratarOpcaoDoCliente(String opcao) throws ClienteException, SQLException {
		sc.nextLine();
		opcao = opcao.toLowerCase();
		opcao = opcao.replace("ã", "a");
		if(opcao.equals("nao") || opcao.equals("n")) {			
			return this.cadastrarCliente();
		}else {
			Cliente cliente = this.buscarPeloCpf(opcao);
			boolean continua = true;
			Integer chance = 3;
			do {
				System.out.println("Digite sua senha: ");
				String senha = sc.next();
				if(!cliente.getSenha().equals(senha)) {
					chance--;
					System.out.println("Senha incorreta, tente novamente. Chances: " + chance);
					if(chance < 0) {
						throw new ClienteException("Número de tentativas excedidas. Faça login novamente");
					}
				}else {
					continua = false;
				}
			}while(continua);
			
			return cliente;
		}
	}

	public void atualizarCliente(Cliente cliente) throws SQLException {
		this.repository.atualizar(cliente);		
	}
	
	public void mostrarVeiculosAlugados(Integer id) throws SQLException {
		
		Cliente cliente = this.repository.buscarPorID(id).get(0);
		
		VeiculosClienteDTO veiculoCliente = this.repository.veiculosAlugadosPorCliente(cliente);
		
		List<Veiculo> veiculos = veiculoCliente.getVeiculos();
//		for(Veiculo veiculo : clienteRepo.getVeiculos()) {
//			System.out.println(veiculo.getId() + " - " +  veiculo.getMarca() + ", " + veiculo.getModelo() + ", " + veiculo.getCor());
//		}
		
		veiculos.forEach(v -> System.out.println(v.getId() + " - " +  v.getMarca() + ", " + v.getModelo() + ", " + v.getCor()));
	}
		

}
