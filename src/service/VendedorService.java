package service;

import java.util.Scanner;

import exceptions.ClienteException;
import exceptions.VendedorException;
import model.Veiculo;
import model.Vendedor;
import model.Veiculo.Tipo;
import repository.RepositoryImpl;
import util.Contador;

public class VendedorService {
	
	private RepositoryImpl<Integer, Vendedor> repository = new RepositoryImpl<>();
	private Scanner sc;
	
	public VendedorService(Scanner sc) {
		this.sc = sc;
		
		Vendedor vendedor1 = new Vendedor( "José", "78945612", "1111");
		Vendedor vendedor2 = new Vendedor("Maria", "545721458", "2222");
		Vendedor vendedor3 = new Vendedor("Joao", "9454201231", "3333");
		Vendedor vendedor4 = new Vendedor("Ana", "51068454555", "4444");
		repository.salvar(vendedor1.getId(), vendedor1);
		repository.salvar(vendedor2.getId(), vendedor2);
		repository.salvar(vendedor3.getId(), vendedor3);
		repository.salvar(vendedor4.getId(), vendedor4);
	}
	
	public void mostrarTodos() {
		for(Vendedor vendedor : repository.buscarTodos()) {
			System.out.println(vendedor.getId() + " - " + vendedor.getNome());
		}
		
	}
	
	public Vendedor buscarVendedorPorId(Integer id) {
		Vendedor vendedor = repository.buscaPorId(id);
		if(vendedor == null) {
			throw new VendedorException("Vendedor não encontrado!");
		}
		
		return vendedor;
	}

	public Vendedor login() {		
		System.out.println("Digite uma opção: ");
		this.mostrarTodos();
		Integer id = sc.nextInt();
		Vendedor vendedor = this.repository.buscaPorId(id);
		
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

	public void cadastrarVendedor() {
		sc.nextLine();
		
		System.out.println("Digite o nome do vendedor");
		String nome = sc.nextLine();
		System.out.println("Digite o cpf do vendedor");
		String cpf = sc.nextLine();
		System.out.println("Digite a senha do vendedor");
		String senha = sc.nextLine();
		
		Vendedor vendedor = new Vendedor(nome, cpf, senha);
		
		repository.salvar(vendedor.getId(), vendedor);		
	}
	
	public void cadastrarVenda(Integer id, Double valorVenda) {
		Vendedor vendedor = this.repository.buscaPorId(id);
		vendedor.setTotalVendas(vendedor.getTotalVendas() + valorVenda);
		this.repository.salvar(vendedor.getId(), vendedor);
	}

	public void mostrarSalario(Integer id) {
		Vendedor vendedor = this.repository.buscaPorId(id);
		
		System.out.println("Seu salário base: " + vendedor.getSalario());
		
		Double salarioTotal = vendedor.getSalario() + (vendedor.getTotalVendas() * Vendedor.COMISSAO);
		System.out.println("Seu salário com comissão: " + salarioTotal);
		
		System.out.println("O valor total de vendas: " + vendedor.getTotalVendas());
	}
}
