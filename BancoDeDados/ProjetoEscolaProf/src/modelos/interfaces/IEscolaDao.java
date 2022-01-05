/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.interfaces;
import modelos.entidades.Escola;
import java.util.List;

/**
 *
 * @author M
 */
//Interface do CRUD
public interface IEscolaDao {
    void incluir(Escola objEscola) throws Exception;
    void alterar(Escola objEscola) throws Exception;
    void excluirPorID(int idEscola) throws Exception;
    Escola consultarPorID(int idEscola) throws Exception;
    List<Escola> listagem() throws Exception;
}
