import repository.RepositoryImpl;
import service.AluguelService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;
import util.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.ClienteException;
import exceptions.VeiculoException;
import exceptions.VendedorException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Vendedor;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		VeiculoService veiculoService = new VeiculoService(sc);
		ClienteService clienteService = new ClienteService(sc);
		VendedorService vendedorService = new VendedorService(sc);
		AluguelService aluguelService = new AluguelService(sc);
		
		boolean continua = true;
		do {
			try {
			Menu.bemVindo();
			Menu.identificar();

			int identificacao = sc.nextInt();

			if (identificacao == 1) {
				Menu.menuCliente1();
				String cadastro = sc.next();
				Cliente cliente = clienteService.tratarOpcaoDoCliente(cadastro);
				Menu.menuCliente2();
				int opcao = sc.nextInt();
				
				if (opcao == 1) {
					System.out.println();
					System.out.println("Escolha uma opção: ");
					veiculoService.mostrarTodosLivres();
					int opcaoVeiculo = sc.nextInt();
					Veiculo veiculo = veiculoService.buscarPorId(opcaoVeiculo);
					
					
					System.out.println();
					System.out.println("Escolha qual vendedor lhe atendeu: ");
					vendedorService.mostrarTodos();
					int opcaoVendedor = sc.nextInt();
					Vendedor vendedor = vendedorService.buscarVendedorPorId(opcaoVendedor);
					aluguelService.salvar(cliente, vendedor, veiculo);
					veiculo.setStatus(Status.ALUGADO);
					veiculo.setCliente(cliente);
					veiculoService.atualizarVeiculo(veiculo);
					cliente.getVeiculos().add(veiculo);
					clienteService.atualizarCliente(cliente);
					vendedorService.cadastrarVenda(vendedor.getId(), veiculo.getValor());
				} else if (opcao == 2) {
					System.out.println("Digite a opção referente ao veículo: ");
					 clienteService.mostrarVeiculosAlugados(cliente.getId());
					 int opcaoVeiculo = sc.nextInt();
					 Veiculo veiculo = veiculoService.buscarPorId(opcaoVeiculo);
					 cliente.getVeiculos().remove(veiculo);
					 veiculo.setStatus(Status.LIVRE);
					 veiculo.setCliente(null);
					 veiculoService.atualizarVeiculo(veiculo);
					 clienteService.atualizarCliente(cliente);
					 
				}
			} else if (identificacao == 2) {
				Vendedor vendedor = vendedorService.login();				
				Menu.menuVendedor();
				int opcaoVendedor = sc.nextInt();
				
				if(opcaoVendedor == 1) {
					vendedorService.mostrarSalario(vendedor.getId());
				}else if(opcaoVendedor == 2) {
					veiculoService.buscarVeiculosAlugados();
				}
			} else if (identificacao == 3) {
				Menu.menuAdministrador();
				int opcao = sc.nextInt();

				if (opcao == 1) {
					veiculoService.cadastrarVeiculo();
				} else if (opcao == 2) {
					vendedorService.cadastrarVendedor();
				}else if(opcao == 3) {
					aluguelService.mostrarTotalVendas();
				}
			} else if (identificacao == 0) {
				continua = false;
			}
			
			}catch(InputMismatchException e) {
				System.out.println("Opção inválida");
				System.out.println("Tente novamente!!");
			}catch(ClienteException e) {
				System.out.println(e.getMessage());
			}catch(VendedorException e) {
				System.out.println(e.getMessage());
			}catch(VeiculoException e) {
				System.out.println(e.getMessage());
			}finally {
				Thread.sleep(2000);
				sc.nextLine();
			}
		} while (continua);
	

	}

}
