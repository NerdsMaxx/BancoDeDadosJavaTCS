/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import bdconexao.ConexaoBD;
import java.util.List;
import modelos.entidades.Escola;
import modelos.interfaces.IEscolaDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author M
 */
public class EscolaJDBCDao implements IEscolaDao {
    //Conexao com o banco
    private Connection conexao = null;
    
    public EscolaJDBCDao() throws Exception {
        conexao = ConexaoBD.getConexao();
    }
    
    public void finalizarConexao() throws Exception {
        ConexaoBD.closeConexao();
    }
    
    private PreparedStatement definirOsValoresDaEscolaSQL(String sql, Escola objEscola) throws Exception{
        try{
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, objEscola.getNome());
            ps.setString(2, objEscola.getTelefone());
            ps.setString(3, objEscola.getEmail());
            ps.setString(4, objEscola.getEndereco());

            return ps;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    private PreparedStatement atualizarOsValoresDaEscolaPeloIDSQL(String sql, Escola objEscola) throws Exception{
        try{
            PreparedStatement ps = definirOsValoresDaEscolaSQL(sql, objEscola);
            ps.setInt(5, objEscola.getId());
            return ps;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    private Escola setarObjetoEscola(ResultSet rs) throws Exception{
        try{
            Escola objEscola = new Escola();
           
            objEscola.setId(rs.getInt("id_escola"));
            objEscola.setNome(rs.getString("nome_escola"));
            objEscola.setTelefone(rs.getString("telefone_escola"));
            objEscola.setEmail(rs.getString("email_escola"));
            objEscola.setEndereco(rs.getString("endereco_escola"));

            return objEscola;
        
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    @Override
    public void incluir(Escola objEscola) throws Exception {
        try {
            String sql = "INSERT INTO escola(nome_escola, telefone_escola, "
                    + "email_escola, endereco_escola) VALUES(?,?,?,?)";
            PreparedStatement ps = definirOsValoresDaEscolaSQL(sql, objEscola);
            ps.executeUpdate();
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Escola objEscola) throws Exception {
        try {
            String sql = "UPDATE escola SET nome_escola = ?," +
                         "telefone_escola = ?, email_escola = ?, " +
                         "endereco_escola = ? WHERE id_escola = ?";
            PreparedStatement ps = atualizarOsValoresDaEscolaPeloIDSQL(sql, objEscola);
            int row = ps.executeUpdate();
            
            if(row == 0){
                throw new Exception("A escola não existe.");
            }
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void excluirPorID(int idEscola) throws Exception {
        try {
            String sql = "DELETE FROM escola WHERE id_escola = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idEscola);
            int row = ps.executeUpdate();
            
            if(row == 0){
                throw new Exception("A escola não existe.");
            }
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public Escola consultarPorID(int idEscola) throws Exception {
        try {
            String sql = "SELECT * FROM escola WHERE id_escola = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);                                         
            ps.setInt(1, idEscola);
            ResultSet rs = ps.executeQuery();
            
            Escola objEscola = null;
            if(rs.next()) {
                objEscola = setarObjetoEscola(rs);
            }
            else{
                throw new Exception("A escola não existe.");
            }
           
            return objEscola;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public List<Escola> listagem() throws Exception {
            try {
            List<Escola> listaDasEscolas = new LinkedList<Escola>();
            String sql = "SELECT * FROM Escola";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Escola objEscola = null;
            while(rs.next()) {    
                objEscola = setarObjetoEscola(rs);
                listaDasEscolas.add(objEscola);
            }
            
            return listaDasEscolas;
            
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, ....
            throw new Exception("SQL ERRO: " + erro.getMessage());
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
