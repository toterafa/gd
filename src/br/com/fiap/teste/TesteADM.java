package br.com.fiap.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.dao.AdministradorDAO;
import br.com.fiap.resource.Administrador;
import br.com.fiap.resource.Endereco;

public class TesteADM {

	public static void main(String[] args) throws SQLException {
		
		Endereco end1 = new Endereco();

        end1.setEstado("SP");
        end1.setCidade("spspspsp");
        end1.setNomeLogradouro("Rua abc");
        end1.setCep("00010-5454");


        AdministradorDAO dao = new AdministradorDAO();

        Administrador adm1 = new Administrador();

        adm1.setNome("não sei");
        adm1.setCpf("124.125.125-12");
        adm1.setGenero("h");
        adm1.setEmail("nao@fiap.com");
        adm1.setSenha("naosei12");
        adm1.setEndereco(end1);
        adm1.setCnpj("1231.456/005401-456");

        dao.insert(adm1);
        
        adm1.setNome("FARR");        
        adm1.setCpf("123.456.789.85");
        adm1.setGenero("H");
        adm1.setEmail("rafaeloooo@fiap.gol");
        adm1.setSenha("12345dew");
        adm1.setEndereco(end1);
        adm1.setMatricula(27);
       
        
        //dao.update(adm1);
        
    
        //dao.delete(adm1);
        
         
//        ArrayList<Administrador> administrador = dao.getAdministrador();
//		
//		for(Administrador a: administrador) {
//			System.out.println("Matrícula: " + a.getMatricula());
//			System.out.println("Nome: " + a.getNome());
//			System.out.println("CPF: " + a.getCpf());
//			System.out.println("E-mail: " + a.getEmail());
//			System.out.println("Genero: " + a.getGenero());
//			System.out.println("Estado: " + a.getEndereco().getEstado());
//			System.out.println("Cidade: " + a.getEndereco().getCidade());
//			System.out.println("Rua/Av/Lougradouro: " + a.getEndereco().getNomeLogradouro());
//			System.out.println("CEP: " + a.getEndereco().getCep());
//			System.out.println("____________________________________________");
//		}
    
    }

	}


