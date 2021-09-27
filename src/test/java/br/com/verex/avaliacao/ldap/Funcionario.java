package br.com.verex.avaliacao.ldap;

public class Funcionario {

    private String nome;
    private CargoStrategy cargo;

    public CargoStrategy getCargo() {
        return cargo;
    }

    public void setCargo(CargoStrategy cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
