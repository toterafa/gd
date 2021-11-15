package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.resource.Administrador;
import br.com.fiap.resource.Endereco;

/**
 * Classe que manipula a tabela 'Administrador' do banco de dados
 * @author Rafael Fernandes
 *
 */
public class AdministradorDAO {
	/**
	 * Método para inserir um administrador.
	 * Para utilizar esse método, o objeto administrador deve possuir obrigatoriamente o número de matrícula.
	 * @param Objeto do tipo administrador.
	 * @throws SQLException
	 */
	public void insert (Administrador administrador) throws SQLException{
			
		Connection conexao = new ConnectionFactory().getConnection();		
		
		PreparedStatement stmt = conexao.prepareStatement(
				  "insert into t_gd_administrador"
				+ "(nm_adm, ds_cpf, ds_email, ds_senha, ds_genero,"
				+ " nm_estado, nm_cidade, ds_endereco, ds_cep, ds_cnpj)"
				+ "values(?,?,?,?,?,?,?,?,?,?)");
		try {
			stmt.setString(1, administrador.getNome());
			stmt.setString(2, administrador.getCpf());
			stmt.setString(3, administrador.getEmail());
			stmt.setString(4, administrador.getSenha());
			stmt.setString(5, administrador.getGenero());
			stmt.setString(6, administrador.getEndereco().getEstado());
			stmt.setString(7, administrador.getEndereco().getCidade());
			stmt.setString(8, administrador.getEndereco().getNomeLogradouro());
			stmt.setString(9, administrador.getEndereco().getCep());
			stmt.setString(10, administrador.getCnpj());

		}catch(NullPointerException e) {
			System.out.println("NullPointerException caught");
		}
		
		try {
			stmt.execute();
			System.out.println("Insert executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já está no Banco de dados");			
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex) {
			ex.printStackTrace();			
		}
	}

	/**
	 * Método que atualiza um registro de administrador.
	 * Para utilizar esse método, o objeto administrador deve possuir obrigatoriamente o número de matrícula.
	 * Deve-se garantir que esse administrador exista no banco.
	 * @param Objeto do tipo administrador.
	 * @throws SQLException
	 */
	public void update (Administrador administrador)throws SQLException{
		
		Connection conexao = new ConnectionFactory().getConnection();	
		PreparedStatement stmt = conexao.prepareStatement(
				 "update t_gd_administrador set nm_adm=?, ds_cpf=?, ds_genero=?, ds_email=?, ds_senha=?, nm_estado=?, nm_cidade=?, ds_endereco=?, ds_cep=?"
				+"where nr_matricula=?");
		try {
			stmt.setString(1, administrador.getNome());
			stmt.setString(2, administrador.getCpf());
			stmt.setString(3, administrador.getGenero());
			stmt.setString(4, administrador.getEmail());
			stmt.setString(5, administrador.getSenha());			
			stmt.setString(6, administrador.getEndereco().getEstado());
			stmt.setString(7, administrador.getEndereco().getCidade());
			stmt.setString(8, administrador.getEndereco().getNomeLogradouro());
			stmt.setString(9, administrador.getEndereco().getCep());			
			stmt.setInt(10, administrador.getMatricula());
			
		}catch(NullPointerException e) {
			System.out.println("NullPointerException caught");
		}
		
		try {
			stmt.execute();
			System.out.println("upDate executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou inserir um valor que já está no Banco de dados");
		}
		try {
			stmt.close();
			conexao.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
			}
		}
	
	/**
	 * Método que exclui um registro de administrador
	 * Para utilizar esse método, o objeto administrador deve possuir obrigatoriamente o número de matrícula.
	 * Deve-se garantir que esse administrador exista no banco.
	 * @param Objeto do tipo administrador.
	 * @throws SQLException
	 */
	public void delete (Administrador administrador) throws SQLException{
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_GD_ADMINISTRADOR WHERE NR_MATRICULA=?");
		
		try {
			stmt.setInt(1, administrador.getMatricula());
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("NullPointerException caught");
		}
		try {
			stmt.execute();
			System.out.println("Delete executado");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Você tentou deletar um valor que não existe no banco");
		}
		try {
			stmt.close();
			conexao.close();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			}
		}
	
	/**
	 * Método que cria uma lista com todos os registros de administrador existentes no banco de dados
	 * @return Objeto do tipo lista
	 * @throws SQLException
	 */
	public ArrayList<Administrador> getAdministrador() throws SQLException{
		ArrayList<Administrador> lista = new ArrayList<>();
		
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = conexao.prepareStatement(
				  "select nr_matricula, nm_adm, ds_cpf, ds_email, ds_genero, nm_estado, nm_cidade, ds_endereco, ds_cep, ds_cnpj from T_GD_ADMINISTRADOR order by nr_matricula");
		try {
			stmt.execute();
			System.out.println("lista de dados de Administrador");
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("Ocorreu um erro ao listar os dados de Administrador");
		}
		
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next()) {
			
			Endereco a = new Endereco();
			
	        a.setEstado(rs.getString("NM_ESTADO"));
	        a.setCidade(rs.getString("NM_CIDADE"));
	        a.setNomeLogradouro(rs.getString("DS_ENDERECO"));
	        a.setCep(rs.getString("DS_CEP"));
			
	        Administrador administrador = new Administrador();
	        
			administrador.setMatricula(rs.getInt("nr_matricula"));
			administrador.setNome(rs.getString("nm_adm"));
			administrador.setCpf(rs.getString("ds_cpf"));
			administrador.setEmail(rs.getString("ds_email"));
			administrador.setGenero(rs.getString("ds_genero"));
			administrador.setEndereco(a);
			administrador.setCnpj(rs.getString("ds_cnpj"));
			
			lista.add(administrador);
		}
		
		try {
			rs.close();
			stmt.close();
			conexao.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
}
