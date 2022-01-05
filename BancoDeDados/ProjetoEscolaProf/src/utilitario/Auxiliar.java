/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;
import java.util.regex.*;
import java.text.Normalizer;

/**
 *
 * @author guilherme
 */
public class Auxiliar{
    
    static public boolean checarOsDados(String nome, String telefone, String email, String endereco){
        return nomeEstaValido(nome) &&
               estaCorretoTelefone(telefone) &&
               estaCorretoEmail(email) &&
               estaPreenchido(endereco);
    }
    
    static public boolean checarOsDados(String nome, String telefone, String email, String endereco, String matricula){
        return nomeEstaValido(nome) &&
               estaCorretoTelefone(telefone) &&
               estaCorretoEmail(email) &&
               estaPreenchido(endereco) &&
               estaCorretoMatricula(matricula);
    }

    static private boolean nomeEstaValido(String nome) {
        String nome_exp_reg = "(([A-Za-z]+\\s)+)?[A-Za-z]+";
        return nome.matches(nome_exp_reg);
    }

    static private boolean estaCorretoTelefone(String telefone) {
        String telefone_exp_reg = "(\\()?(\\d{2})(\\))?(\\s)?(\\d{4,5})(-|\\s)?(\\d{4})";
        return telefone.matches(telefone_exp_reg);
    }

    static private boolean estaCorretoEmail(String email) {
        String email_exp_reg = ".+@.+\\.[a-z]+(\\.br)?";
        return email.matches(email_exp_reg);
    }
    
    static private boolean estaCorretoMatricula(String matricula){
        String matricula_exp_reg = "\\d{1,10}";
        return matricula.matches(matricula_exp_reg);
    }
    
    static private boolean estaPreenchido(String str){
        return !str.isBlank();
    }
    
    /*static public String removeAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }*/
    
    static public String ajustarTelefoneParaFormatoPadrao(String telefone){
        String telefone_exp_reg = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";
        if(!telefone.matches(telefone_exp_reg)){
            telefone = telefone.replaceAll("(\\()?(\\d{2})(\\))?(\\s)?(\\d{4,5})(-|\\s)?(\\d{4})",
                                           "($2) $5-$7");
        }
        return telefone;
    }
}
