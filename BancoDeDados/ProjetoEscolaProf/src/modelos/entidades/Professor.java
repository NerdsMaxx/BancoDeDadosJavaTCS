/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;
import utilitario.Auxiliar;
/**
 *
 * @author M
 */
public class Professor {
    //Atributos
    private int id = 0;
    private int matricula = 0;
    private String nome = "";
    private String telefone = "";
    private String email = "";
    private Escola escola = null;
    private String endereco = "";
    private Titulacao titucao = Titulacao.Graduacao;
    
    //Metodos
    public Professor() {}
    
    public Professor(int id, int matricula, String nome, String telefone, String email, Escola escola, String endereco, Titulacao titulacao) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.escola = escola;
        this.endereco = endereco;
        this.titucao = titulacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Titulacao getTitucao() {
        return titucao;
    }

    public void setTitucao(Titulacao titucao) {
        this.titucao = titucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    @Override
    public String toString() {
        return "Professor:\n" + 
                "id: " + id + '\n' +
                "matricula: " + matricula + '\n' +
                "nome: " + nome + '\n' +
                "titucao: " + titucao + '\n' +
                "telefone: " + telefone + '\n' + 
                "email: " + email + '\n' +
                "escola: " + escola.getId() + " - " + escola.getNome() + '\n' +
                "endereco: " + endereco + '\n';
    }
    

}
