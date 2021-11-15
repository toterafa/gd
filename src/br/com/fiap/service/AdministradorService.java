package br.com.fiap.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.AdministradorDAO;
import br.com.fiap.resource.Administrador;

public class AdministradorService {

	
    private AdministradorDAO dao;


    public AdministradorService() {
        dao = new AdministradorDAO();
    }
 //insert
    public void inserir(Administrador a) throws SQLException{
        ArrayList<Administrador> Administrador = dao.getAdministrador();
        ArrayList<Integer> listaIdAdministrador = new ArrayList<Integer>();
        
        for(Administrador u1 : Administrador) {
            listaIdAdministrador.add(u1.getMatricula());
        }
        
        if(a.getMatricula() < 1 || a.getMatricula() > 99999) {
            System.out.println("O ID do usuário deve ser um número entre 1 e 99.999");
        }
        else if(listaIdAdministrador.contains(a.getMatricula())) {
            System.out.println("Esse ID de usuário não existe no banco de dados");
        }
        else if(a.getNome().length() < 2 || a.getNome().length() > 40) {
            System.out.println("O nome do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
        }
        else if(a.getEmail().length() < 2 || a.getEmail().length() > 40) {
            System.out.println("O email do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
        }
        else if(a.getSenha().length() < 1 || a.getSenha().length() > 12){
            if(a.getSenha().length() <=3 ) {
                System.out.println("A senha do usuário é muito fraca");
            }
            System.out.println("A senha do usuário deve ter mais do que 1 caracter e no máximo 12");
        }    
        else if(a.getGenero().toUpperCase() != "H" && a.getGenero().toUpperCase() != "M" && a.getGenero().toUpperCase() != "NB") {
            System.out.println("O gênero deve ser 'H' para homem, 'M' para mulher e 'NB' para não-binário");
        }
        else if(a.getEndereco().getEstado().length() != 2) {
            System.out.println("O Estado doi definido incorretamente. Escolha uma das opções disponíveis");
        }
        else if(a.getEndereco().getCidade().length() < 3 || a.getEndereco().getCidade().length() > 30) {
            System.out.println("Nome da cidade deve conter entre 4 a 30 caracteres");        
        }
        else if(a.getEndereco().getNomeLogradouro().length() <= 0 || a.getEndereco().getNomeLogradouro().length() > 40) {
            System.out.println("O endereço deve conter até 40 caracteres, se necessário abrevie algum nome.");
        }
        else if(a.getEndereco().getCep().length() != 9) {
            System.out.println("Inisira o cep com a devida pontuação no seguinte modelo: xxxxx-xxx ");
        }
        else {
            dao.insert(a);
        }
    }
// update    
    public void atualizar(Administrador a) throws SQLException{
        ArrayList<Administrador> administrador = dao.getAdministrador();
        ArrayList<Integer> listaIdAdministrador = new ArrayList<Integer>();
        
        for(Administrador u1 : administrador) {
            listaIdAdministrador.add(u1.getMatricula());
        }        
        if(!listaIdAdministrador.contains(a.getMatricula())) {
            System.out.println("Só é possível atualizar um usuário que já existe no banco, forneça outro ID");
        }else if(a.getNome().length() < 2 || a.getNome().length() > 40) {
            System.out.println("O nome do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
        }
        else if(a.getEmail().length() < 2 || a.getEmail().length() > 40) {
            System.out.println("O email do usuário não pode ter menos do que 2 caracteres e não pode ter mais do que 40 caracteres");
        }
        else if(a.getSenha().length() < 1 || a.getSenha().length() > 12){
            if(a.getSenha().length() <=3 ) {
                System.out.println("A senha do usuário é muito fraca");
            }
            System.out.println("A senha do usuário deve ter mais do que 1 caracter no máximo 12");
        }    
        else if(a.getGenero().toUpperCase() != "H" && a.getGenero().toUpperCase() != "M" && a.getGenero().toUpperCase() != "NB") {
            System.out.println("O gênero deve ser 'H' para homem, 'M' para mulher e 'NB' para não-binário");
        }
        else if(a.getEndereco().getEstado().length() != 2) {
            System.out.println("O Estado doi definido incorretamente. Escolha uma das opções disponíveis");
        }
        else if(a.getEndereco().getCidade().length() < 3 || a.getEndereco().getCidade().length() > 30) {
            System.out.println("Nome da cidade deve conter entre 4 a 30 caracteres");        
        }
        else if(a.getEndereco().getNomeLogradouro().length() <= 0 || a.getEndereco().getNomeLogradouro().length() > 40) {
            System.out.println("O endereço deve conter até 40 caracteres, se necessário abrevie algum nome.");
        }
        else if(a.getEndereco().getCep().length() != 9) {
            System.out.println("Inisira o cep com a devida pontuação no seguinte modelo: xxxxx-xxx ");
        }
            dao.update(a);            
    }
    public ArrayList<Administrador> listar() throws SQLException {
        try {
            ArrayList<Administrador> administrador = dao.getAdministrador();
            for(Administrador a: administrador) {
    			System.out.println("Matrícula: " + a.getMatricula());
    			System.out.println("Nome: " + a.getNome());
    			System.out.println("CPF: " + a.getCpf());
    			System.out.println("E-mail: " + a.getEmail());
    			System.out.println("Genero: " + a.getGenero());
    			System.out.println("Estado: " + a.getEndereco().getEstado());
    			System.out.println("Cidade: " + a.getEndereco().getCidade());
    			System.out.println("Rua/Av/Lougradouro: " + a.getEndereco().getNomeLogradouro());
    			System.out.println("CEP: " + a.getEndereco().getCep());
    			System.out.println("____________________________________________");
    		}
        }catch(SQLException e) {
            System.out.println("Houve um erro na listagem, verifique se há dados no banco");
        }
		return dao.getAdministrador();
    }
    
	public void deletar(Administrador a) throws SQLException {
		ArrayList<Administrador> administrador = dao.getAdministrador();
		ArrayList<Integer> listaIdAdministrador = new ArrayList<Integer>();
		for(Administrador a1 : administrador) {
			listaIdAdministrador.add(a1.getMatricula());
		}
		
		if(!listaIdAdministrador.contains(a.getMatricula())) {
			System.out.println("É necessário informar um ID para deletar a pergunta");
		}else {
			dao.delete(a);			
		}
	}
    
    
}
