package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import exceptions.ClienteException;
import exceptions.VendedorException;
import model.Veiculo;
import model.Vendedor;
import model.Veiculo.Tipo;
import repository.RepositoryImpl;
import repository.VendedorRepository;
import util.Contador;

public class VendedorService {
	
	private VendedorRepository repository = new VendedorRepository();
	private Scanner sc;
	
	public VendedorService(Scanner sc) {
		this.sc = sc;
	}
	
	public void mostrarTodos() throws SQLException {
		
		List<Vendedor> vendedores = repository.buscarTodos();
		
		vendedores.forEach(v -> System.out.println(v.getId() + " - " + v.getNome()));
	}
	
	public Vendedor buscarVendedorPorId(Integer id) throws SQLException {
		Vendedor vendedor = repository.buscarPorID(id).get(0);
		if(vendedor == null) {
			throw new VendedorException("Vendedor não encontrado!");
		}
		
		return vendedor;
	}

	public Vendedor login() throws SQLException {		
		System.out.println("Digite uma opção: ");
		this.mostrarTodos();
		Integer id = sc.nextInt();
		Vendedor vendedor = this.repository.buscarPorID(id).get(0);
		
		boolean continua = true;
		Integer chance = 3;
		do {
			System.out.println("Digite sua senha: ");
			String senha = sc.next();
			if(!vendedor.getSenha().equals(senha)) {
				chance--;
				System.out.println("Senha incorreta, tente novamente. Chances: " + chance);
				if(chance < 0) {
					throw new VendedorException("Número de tentativas excedidas. Faça login novamente");
				}
			}else {
				continua = false;
			}
		}while(continua);
		
		return vendedor;
	}

	public void cadastrarVendedor() throws SQLException {
		sc.nextLine();
		
		System.out.println("Digite o nome do vendedor");
		String nome = sc.nextLine();
		System.out.println("Digite o cpf do vendedor");
		String cpf = sc.nextLine();
		System.out.println("Digite a senha do vendedor");
		String senha = sc.nextLine();
		
		Vendedor vendedor = new Vendedor(nome, cpf, senha);
		
		repository.salvar(vendedor);		
	}
	
	public void cadastrarVenda(Integer id, Double valorVenda) throws SQLException {
		Vendedor vendedor = this.repository.buscarPorID(id).get(0);
		vendedor.setTotalVendas(vendedor.getTotalVendas() + valorVenda);
		this.repository.salvar(vendedor);
	}

	public void mostrarSalario(Integer id) throws SQLException {
		Vendedor vendedor = this.repository.buscarPorID(id).get(0);
		
		System.out.println("Seu salário base: " + vendedor.getSalario());
		
		Double salarioTotal = vendedor.getSalario() + (vendedor.getTotalVendas() * Vendedor.COMISSAO);
		System.out.println("Seu salário com comissão: " + salarioTotal);
		
		System.out.println("O valor total de vendas: " + vendedor.getTotalVendas());
	}
}
