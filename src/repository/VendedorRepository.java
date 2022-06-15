package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vendedor;
import model.Vendedor;

public class VendedorRepository {
	PreparedStatement ps;
	Conexao conexao = new Conexao();
	
	public void salvar(Vendedor vendedor) throws SQLException {
		
		try {
		String query = "INSERT INTO vendedores (nome, cpf, senha, salario) "
					 + "values (?, ?, ?, ?)";
		
		ps = conexao.getConexao().prepareStatement(query);
		
		ps.setString(1, vendedor.getNome());
		ps.setString(2, vendedor.getCpf());
		ps.setString(3, vendedor.getSenha());
		ps.setDouble(4, vendedor.getSalario());
		
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
	
	public List<Vendedor> buscarTodos() throws SQLException{
		String query = "select * from vendedores";
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Vendedor> vendedores = new ArrayList<>();
			
			while(result.next()) {
				Vendedor vendedor = new Vendedor(result.getString("nome"), result.getString("cpf"), 
											  result.getString("senha"));
				vendedor.setId(result.getInt("id"));
				
				
				vendedores.add(vendedor);
			}
			
			return vendedores;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar todos: " + e.getCause());
		}finally {
			ps.close();
		}
	}
	
	public List<Vendedor> buscarPorID(Integer id) throws SQLException{
		String query = "select * from vendedores where id = " + id;
		try {
			ps = conexao.getConexao().prepareStatement(query);
			ResultSet result = ps.executeQuery();
			
			List<Vendedor> vendedores = new ArrayList<>();
			
			while(result.next()) {
				Vendedor vendedor = new Vendedor(result.getString("nome"), result.getString("cpf"), 
						  							result.getString("senha"));
				vendedor.setId(result.getInt("id"));
				
				vendedores.add(vendedor);
			}
			
			return vendedores;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao buscar vendedor: " + e.getCause());
		}finally {
			ps.close();
		}
	}
	
	public void atualizar(Vendedor vendedor) throws SQLException {
		try {
			String query = "UPDATE vendedores set nome = ? , cpf = ?, senha = ? where id = " + vendedor.getId();
			
			ps = conexao.getConexao().prepareStatement(query);
			
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getCpf());
			ps.setString(3, vendedor.getSenha());
			
			int cadastrou = ps.executeUpdate();
			
			if(cadastrou == 1) {
				System.out.println("Atualizado com sucesso!");
			}else {
				System.out.println("Não foi atualizado com sucesso!");
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("Erro ao atualizar: " + e.getCause());
			}finally {
				ps.close();
			}
	}
	
	public void excluirPorID(Integer id) throws SQLException {
		String query = "delete from vendedores where id = " + id;
		
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
		}finally {
			ps.close();
		}
	}
}
