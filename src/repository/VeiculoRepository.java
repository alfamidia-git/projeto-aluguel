package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;

public class VeiculoRepository {
	
	PreparedStatement ps;
	Conexao conexao = new Conexao();
	
	public void salvar(Veiculo veiculo) throws SQLException {
		
		try {
		String query = "INSERT INTO veiculos (modelo, placa, marca, status, valor, cor, tipo) "
					 + "values (?, ?, ?, ?, ?, ?, ?)";
		
		ps = conexao.getConexao().prepareStatement(query);
		
		ps.setString(1, veiculo.getModelo());
		ps.setString(2, veiculo.getPlaca());
		ps.setString(3, veiculo.getMarca());
		ps.setString(4, veiculo.getStatus().toString());
		ps.setDouble(5, veiculo.getValor());
		ps.setString(7, veiculo.getCor());
		ps.setString(8, veiculo.getTipo().toString());
		
		int cadastrou = ps.executeUpdate();
		
		if(cadastrou == 1) {
			System.out.println("Inserido com sucesso!");
		}else {
			System.out.println("Não foi inserido com sucesso!");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao salvar: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	public List<Veiculo> buscarTodos() throws SQLException{
		String query = "select * from veiculos";
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Veiculo> veiculos = new ArrayList<>();
			
			while(result.next()) {
				Veiculo veiculo = new Veiculo(result.getString("placa"), result.getString("modelo"), 
											  result.getString("marca"), result.getString("cor"), result.getDouble("valor"),
											  Tipo.valueOf(result.getString("tipo").toUpperCase()));
				veiculo.setId(result.getInt("id"));
				veiculo.setStatus(Status.valueOf(result.getString("status").toUpperCase()));
				
				veiculos.add(veiculo);
			}
			
			return veiculos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar todos: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	
	public List<Veiculo> buscarPorID(Integer id) throws SQLException{
		String query = "select * from veiculos where id = " + id;
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Veiculo> veiculos = new ArrayList<>();
			
			while(result.next()) {
				Veiculo veiculo = new Veiculo(result.getString("placa"), result.getString("modelo"), 
											  result.getString("marca"), result.getString("cor"), result.getDouble("valor"),
											  Tipo.valueOf(result.getString("tipo").toUpperCase()));
				veiculo.setId(result.getInt("id"));
				veiculo.setStatus(Status.valueOf(result.getString("status").toUpperCase()));
				
				veiculos.add(veiculo);
			}
			
			return veiculos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao salvar: " + e.getCause());
		} finally {
			ps.close();
		}
	}
	
	public void atualizar(Veiculo veiculo) throws SQLException {
		try {
			String query = "UPDATE veiculos set modelo = ? , placa = ?, marca = ?, status = ?, valor = ?, "
						 + "cor = ?, tipo = ? where id = " + veiculo.getId();
			
			ps = conexao.getConexao().prepareStatement(query);
			
			ps.setString(1, veiculo.getModelo());
			ps.setString(2, veiculo.getPlaca());
			ps.setString(3, veiculo.getMarca());
			ps.setString(4, veiculo.getStatus().toString());
			ps.setDouble(5, veiculo.getValor());
			ps.setString(6, veiculo.getCor());
			ps.setString(7, veiculo.getTipo().toString());
			
			int cadastrou = ps.executeUpdate();
			
			if(cadastrou == 1) {
				System.out.println("Atualizado com sucesso!");
			}else {
				System.out.println("Não foi salvo com sucesso!");
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("Erro ao salvar: " + e.getCause());
			} finally {
				ps.close();
			}
	}
	
	public void excluirPorID(Integer id) throws SQLException {
		String query = "delete from veiculos where id = " + id;
		
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
	
	
	public List<Veiculo> buscarVeiculosAlugados() throws SQLException{
		String query = "select * from veiculos where status = 'ALUGADO' ";
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Veiculo> veiculos = new ArrayList<>();
			
			while(result.next()) {
				Veiculo veiculo = new Veiculo(result.getString("placa"), result.getString("modelo"), 
											  result.getString("marca"), result.getString("cor"), result.getDouble("valor"),
											  Tipo.valueOf(result.getString("tipo").toUpperCase()));
				veiculo.setId(result.getInt("id"));
				veiculo.setStatus(Status.valueOf(result.getString("status").toUpperCase()));
				
				veiculos.add(veiculo);
			}
			
			return veiculos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar veiculos alugados: " + e.getCause());
		} finally {
			ps.close();
		}
	}

}
