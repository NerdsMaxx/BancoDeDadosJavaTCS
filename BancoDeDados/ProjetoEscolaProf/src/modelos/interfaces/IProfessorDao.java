/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.interfaces;
import modelos.entidades.Professor;
import java.util.List;

/**
 *
 * @author M
 */
//Interface do CRUD
public interface IProfessorDao {
    void incluir(Professor objProfessor) throws Exception;
    void alterar(Professor objProfessor) throws Exception;
    void excluirPorID(int idProfessor) throws Exception;
    Professor consultarPorID(int idProfessor) throws Exception;
    List<Professor> listagem() throws Exception;
}
