/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;
import java.util.List;
import modelos.entidades.Escola;
import modelos.interfaces.IEscolaDao;
import persistencia.EscolaJDBCDao;
import utilitario.Auxiliar;
/**
 *
 * @author guilherme
 */
public class EscolaControle implements IEscolaDao{
    //atributo
    private EscolaJDBCDao escolaPersistenciaBD = null;
    
    public EscolaControle() throws Exception{
        try{
            escolaPersistenciaBD = new EscolaJDBCDao();
        } catch (Exception erro){
            throw erro;
        }
    }
    
    @Override
    public void incluir(Escola objEscola) throws Exception {
        if(Auxiliar.checarOsDados(objEscola.getNome(), objEscola.getTelefone(), objEscola.getEmail(), objEscola.getEndereco())){
            objEscola.setTelefone(Auxiliar.ajustarTelefoneParaFormatoPadrao(objEscola.getTelefone()));
            escolaPersistenciaBD.incluir(objEscola);
        }
        else{
            throw new Exception("Erro: Um campo esta errado.");
        }
    }

    @Override
    public void alterar(Escola objEscola) throws Exception {
        if(Auxiliar.checarOsDados(objEscola.getNome(), objEscola.getTelefone(), objEscola.getEmail(), objEscola.getEndereco())){
            objEscola.setTelefone(Auxiliar.ajustarTelefoneParaFormatoPadrao(objEscola.getTelefone()));
            escolaPersistenciaBD.alterar(objEscola);
        }
        else{
            throw new Exception("Erro: Um campo esta errado.");
        }
    }

    @Override
    public void excluirPorID(int idEscola) throws Exception {
        try{
            escolaPersistenciaBD.excluirPorID(idEscola);
        } catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public Escola consultarPorID(int idEscola) throws Exception {
        try{
           Escola objEscola = escolaPersistenciaBD.consultarPorID(idEscola);
           return objEscola;
        } catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public List<Escola> listagem() throws Exception {
        try{
            List<Escola> escolas = escolaPersistenciaBD.listagem();
            return escolas;
        } catch(Exception erro){
            throw erro;
        }
        
    }
    
}
