/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import bdconexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import modelos.entidades.Professor;
import modelos.entidades.Titulacao;
import modelos.interfaces.IProfessorDao;
import persistencia.EscolaJDBCDao;

/**
 *
 * @author guilherme
 */
public class ProfessorJDBCDao implements IProfessorDao{
        //Conexao com o banco
    private Connection conexao = null;
    
    public ProfessorJDBCDao() throws Exception {
        conexao = ConexaoBD.getConexao();
    }
    
    public void finalizarConexao() throws Exception {
        ConexaoBD.closeConexao();
    }
    
    private PreparedStatement definirOsValoresDoProfessorSQL(String sql, Professor objProfessor) throws Exception{
        try{
            PreparedStatement ps = conexao.prepareStatement(sql);
        
            ps.setInt(1, objProfessor.getMatricula());
            ps.setString(2, objProfessor.getNome());
            ps.setString(3, objProfessor.getTelefone());
            ps.setString(4, objProfessor.getEmail());
            ps.setString(5, objProfessor.getEndereco());
            ps.setString(6, objProfessor.getTitucao().name());
            ps.setInt(7, objProfessor.getEscola().getId());
            
            return ps;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    private PreparedStatement atualizarOsValoresDoProfessorPeloIDSQL(String sql, Professor objProfessor) throws Exception{
        try{
            PreparedStatement ps = definirOsValoresDoProfessorSQL(sql, objProfessor);
            ps.setInt(8, objProfessor.getId());
            return ps;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    private Professor setarObjetoProfessor(ResultSet rs) throws Exception{
        try{
            EscolaJDBCDao escolaSQL = new EscolaJDBCDao();
            Professor objProfessor = new Professor();
           
            objProfessor.setId(rs.getInt("id_prof"));
            objProfessor.setMatricula(rs.getInt("mat_prof"));
            objProfessor.setNome(rs.getString("nome_prof"));
            objProfessor.setTelefone(rs.getString("telefone_prof"));
            objProfessor.setEmail(rs.getString("email_prof"));
            objProfessor.setEscola(escolaSQL.consultarPorID(rs.getInt("id_escola")));
            objProfessor.setEndereco(rs.getString("endereco_prof"));
            objProfessor.setTitucao(Titulacao.valueOf(rs.getString("titulacao_prof")));

            return objProfessor;
        
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    @Override
    public void incluir(Professor objProfessor) throws Exception {
        try {
            String sql = "INSERT INTO professor(mat_prof, nome_prof, telefone_prof, "
                        + "email_prof, endereco_prof, titulacao_prof, id_escola) "
                        + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = definirOsValoresDoProfessorSQL(sql, objProfessor);
            ps.executeUpdate();
        }  catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        }  catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Professor objProfessor) throws Exception {
        try {
            String sql = "UPDATE professor SET mat_prof = ?, " +
                         "nome_prof = ?, telefone_prof = ?, " +
                         "email_prof = ?, endereco_prof = ?, " +
                         "titulacao_prof = ?, id_escola = ?" +
                         "WHERE id_prof = ?";
            PreparedStatement ps = atualizarOsValoresDoProfessorPeloIDSQL(sql, objProfessor);
            int row = ps.executeUpdate();
            
            if(row == 0){
                throw new Exception("O professor não existe.");
            }
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluirPorID(int idProfessor) throws Exception {
        try {
            String sql = "DELETE FROM professor WHERE id_prof = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idProfessor);
            int row = ps.executeUpdate();
            
            if(row == 0){
                throw new Exception("O professor não existe.");
            }
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public Professor consultarPorID(int idProfessor) throws Exception {
        try {
            String sql = "SELECT * FROM professor WHERE id_prof = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);                                         
            ps.setInt(1, idProfessor);
            ResultSet rs = ps.executeQuery();
            
            Professor objProfessor = null;
            if(rs.next()) {    
                objProfessor = setarObjetoProfessor(rs);
            }
            else{
                throw new Exception("O professor não existe.");
            }
            
            return objProfessor;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public List<Professor> listagem() throws Exception {
        try {
            List<Professor> listaDosProfessores = new LinkedList<Professor>();
            String sql = "SELECT * FROM professor";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Professor objProfessor = null;
            while(rs.next()) {    
                objProfessor = setarObjetoProfessor(rs);
                listaDosProfessores.add(objProfessor);
            }
            
            return listaDosProfessores;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
}
