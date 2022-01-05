/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

/**
 *
 * @author M
 */
public class Escola {
    //Atributos
    private int id = 0;
    private String nome = "";
    private String telefone = "";
    private String email = "";
    private String endereco = "";
    
    //Metodos
    public Escola() {}
    
    public Escola(int id, String nome, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Escola:\n" + 
                "id: " + id + '\n' +
                "nome: " + nome + '\n' + 
                "telefone: " + telefone + '\n' + 
                "email: " + email + '\n' +
                "endereco: " + endereco + '\n';
    }
    
}
