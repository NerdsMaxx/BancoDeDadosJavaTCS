/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import utilitario.Auxiliar;
import java.util.List;
import modelos.entidades.Professor;
import modelos.interfaces.IProfessorDao;
import persistencia.ProfessorJDBCDao;

/**
 *
 * @author guilherme
 */
public class ProfessorControle implements IProfessorDao{
    //atributo
    private ProfessorJDBCDao professorPersistenciaBD = null;

    public ProfessorControle() throws Exception{
        try{
            professorPersistenciaBD = new ProfessorJDBCDao();
        } catch (Exception erro){
            throw erro;
        }
    }
    
    
    @Override
    public void incluir(Professor objProfessor) throws Exception {
        if(Auxiliar.checarOsDados(objProfessor.getNome(), objProfessor.getTelefone(), objProfessor.getEmail(), objProfessor.getEndereco(), String.valueOf(objProfessor.getMatricula()))){
            objProfessor.setTelefone(Auxiliar.ajustarTelefoneParaFormatoPadrao(objProfessor.getTelefone()));
            professorPersistenciaBD.incluir(objProfessor);
        }
        else{
            throw new Exception("Erro: Um campo esta errado.");
        }
    }

    @Override
    public void alterar(Professor objProfessor) throws Exception {
        if(Auxiliar.checarOsDados(objProfessor.getNome(), objProfessor.getTelefone(), objProfessor.getEmail(), objProfessor.getEndereco(), String.valueOf(objProfessor.getMatricula()))){
            objProfessor.setTelefone(Auxiliar.ajustarTelefoneParaFormatoPadrao(objProfessor.getTelefone()));
            professorPersistenciaBD.alterar(objProfessor);
        }
        else{
            throw new Exception("Erro: Um campo esta errado.");
        }
    }

    @Override
    public void excluirPorID(int idProfessor) throws Exception {
        try{
            professorPersistenciaBD.excluirPorID(idProfessor);
        } catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public Professor consultarPorID(int idProfessor) throws Exception {
        try{
           Professor objProfessor = professorPersistenciaBD.consultarPorID(idProfessor);
           return objProfessor;
        } catch(Exception erro){
            throw erro;
        }        
    }

    @Override
    public List<Professor> listagem() throws Exception {
        try{
            List<Professor> professores = professorPersistenciaBD.listagem();
            return professores;
        } catch(Exception erro){
            throw erro;
        }
    }
    
    
    
}