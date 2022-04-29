package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

public class funcionarioDAO extends DAO {
	public funcionarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Funcionario funcionario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO \"public\".\"funcionario\" (nome, biografia, rg, cpf) " + "VALUES ('"
					+ funcionario.getNome() + "', '" + funcionario.getBio() + "', '" + funcionario.getRg() + "', '"
					+ funcionario.getCpf() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Funcionario get(int id) {
		Funcionario funcionario = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"public\".\"funcionario\" WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("biografia"),
						rs.getString("rg"), rs.getString("cpf"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionario;
	}

	public List<Funcionario> get() {
		return get("");
	}

	public List<Funcionario> getOrderByID() {
		return get("id");
	}

	public List<Funcionario> getOrderByNome() {
		return get("nome");
	}

	public List<Funcionario> getOrderByCpf() {
		return get("cpf");
	}

	private List<Funcionario> get(String orderBy) {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"public\".\"funcionario\""
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Funcionario f = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("biografia"),
						rs.getString("rg"), rs.getString("cpf"));
				funcionarios.add(f);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionarios;
	}

	public boolean update(Funcionario funcionario) {
		boolean status = false;
		try {
			String sql = "UPDATE \"public\".\"funcionario\" SET nome = '" + funcionario.getNome() + "' , biografia= '"
					+ funcionario.getBio() + "'," + "rg = '" + funcionario.getRg() + "', cpf = '" + funcionario.getCpf() + "' "
					+ "WHERE id = ? ;";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, funcionario.getID());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean delete(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM \"public\".\"funcionario\" WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}