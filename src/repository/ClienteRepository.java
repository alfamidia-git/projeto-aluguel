package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.VeiculosClienteDTO;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;

public class ClienteRepository {
	PreparedStatement ps;
	Conexao conexao = new Conexao();
	
	public void salvar(Cliente cliente) throws SQLException {
		
		try {
		String query = "INSERT INTO clientes (nome, cpf, senha) "
					 + "values (?, ?, ?)";
		
		ps = conexao.getConexao().prepareStatement(query);
		
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getCpf());
		ps.setString(3, cliente.getSenha());
		
		int cadastrou = ps.executeUpdate();
		
		if(cadastrou == 1) {
			System.out.println("Inserido com sucesso!");
		}else {
			System.out.println("Não foi inserido com sucesso!");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao salvar: " + e.getCause());
		}finally {
			ps.close();
		}
	}
	
	public List<Cliente> buscarTodos() throws SQLException{
		String query = "select * from clientes";
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			
			while(result.next()) {
				Cliente cliente = new Cliente(result.getString("nome"), result.getString("cpf"), 
											  result.getString("senha"));
				cliente.setId(result.getInt("id"));
				
				
				clientes.add(cliente);
			}
			
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar todos: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	public List<Cliente> buscarPorID(Integer id) throws SQLException{
		String query = "select * from clientes where id = " + id;
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			
			while(result.next()) {
				Cliente cliente = new Cliente(result.getString("nome"), result.getString("cpf"), 
						  							result.getString("senha"));
				cliente.setId(result.getInt("id"));
				
				clientes.add(cliente);
			}
			
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar cliente: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	public void atualizar(Cliente cliente) throws SQLException {
		try {
			String query = "UPDATE clientes set nome = ? , cpf = ?, senha = ? where id = " + cliente.getId();
			
			ps = conexao.getConexao().prepareStatement(query);
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getSenha());
			
			int cadastrou = ps.executeUpdate();
			
			if(cadastrou == 1) {
				System.out.println("Atualizado com sucesso!");
			}else {
				System.out.println("Não foi atualizado com sucesso!");
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("Erro ao atualizar: " + e.getCause());
			} finally {
				ps.close();
			}
	}
	
	public void excluirPorID(Integer id) throws SQLException {
		String query = "delete from clientes where id = " + id;
		
		try {
			ps = conexao.getConexao().prepareStatement(query);
			
			int excluiu = ps.executeUpdate();
			if(excluiu == 1) {
				System.out.println("Excluido com sucesso!");
			}else {
				System.out.println("Não foi excluido com sucesso!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Erro ao excluir: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	public VeiculosClienteDTO veiculosAlugadosPorCliente(Cliente cliente) throws SQLException{
		String query = "select id_cliente, id_veiculo, modelo, marca, cor, tipo from alugueis inner join veiculos on veiculos.id = alugueis.id_veiculo"
				+ " where id_cliente = " + cliente.getId();
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			VeiculosClienteDTO dto = new VeiculosClienteDTO();
			
			List<Veiculo> veiculos = new ArrayList<>();
			
			while(result.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setModelo(result.getString("modelo"));
				veiculo.setId(result.getInt("id_veiculo"));
				veiculo.setMarca(result.getString("marca"));
				veiculo.setCor(result.getString("cor"));
				veiculo.setTipo(Tipo.valueOf(result.getString("tipo").toUpperCase()));
				
				veiculos.add(veiculo);
			}
			
			dto.setVeiculos(veiculos);
			dto.setCliente(cliente);
			dto.setData(new Date());
			
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao salvar: " + e.getCause());
		} finally {
			ps.close();
		}
	}
}
