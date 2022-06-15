package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Aluguel;
import model.Vendedor;

public class AluguelRepository {
	PreparedStatement ps;
	Conexao conexao = new Conexao();
	
	public void salvar(Aluguel aluguel) throws SQLException {
		try {
			String query = "INSERT INTO alugueis (id_cliente, id_vendedor, id_veiculo, data) "
						 + "values (?, ?, ?, ?)";
			
			ps = conexao.getConexao().prepareStatement(query);
			
			ps.setInt(1, aluguel.getIdCliente());
			ps.setInt(2, aluguel.getIdVendedor());
			ps.setInt(3, aluguel.getIdVeiculo());
			ps.setString(4, aluguel.getData().toString());
			
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
	
	public void excluirAluguelPorVeiculoID(Integer id) throws SQLException {
		try {
			String query = "delete from alugueis where id_veiculo = " + id; 
			ps = this.conexao.getConexao().prepareStatement(query);
			
			int i = ps.executeUpdate();
			if(i == 1) {
				System.out.println("Foi excluido com sucesso");
			}else {
				System.out.println("Não foi excluido");
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("Erro ao excluir por id do veiculo: " + e.getCause());
		} finally {
			ps.close();
		}
	}
}
