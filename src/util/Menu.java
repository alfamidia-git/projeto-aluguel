package util;

public class Menu {
	
	public static void bemVindo() {
		System.out.println("============================================");
		System.out.println("Bem vindo ao sistema de aluguel de veículos");
		System.out.println("============================================");
	}
	
	public static void identificar() {
		System.out.println();
		System.out.println("Digite uma opção:");
		System.out.println("1 - Cliente");
		System.out.println("2 - Vendedor");
		System.out.println("3 - Administrador");
		System.out.println("0 - Sair do sistema");
	}
	
	public static void menuCliente1() {
		System.out.println();
		System.out.println("Você já tem cadastro?");
		System.out.println("Sim? Digite seu cpf");
		System.out.println("Não? Digite 'não'");
	}
	
	public static void menuCliente2() {
		System.out.println();
		System.out.println("Digite uma opção:");
		System.out.println("1 - Alugar um veículo");
		System.out.println("2 - Devolver um veículo");
	}
	
	public static void menuVendedor() {
		System.out.println();
		System.out.println("Digite uma opção:");
		System.out.println("1 - Ver seu salário atual + comissão");
		System.out.println("2 - Ver carros alugados");
	}
	
	public static void menuAdministrador() {
		System.out.println();
		System.out.println("Digite uma opção:");
		System.out.println("1 - Cadastrar veículo");
		System.out.println("2 - Cadastrar vendedor");
		System.out.println("3 - Ver total de vendas");
	}
	
	public static void alugarVeiculo() {
		System.out.println();
		System.out.println("Digite uma opção:");
		System.out.println("1 - Alugar um veículo");
		System.out.println("2 - Devolver um veículo");
	}
	

}
