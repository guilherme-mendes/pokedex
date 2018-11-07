package model;

/**
 * Classe que armazena os dados os treinadores.
 */
public class Treinador {
    private int id;
    private String nome;
    private String regiao;

    Treinador(int id, String nome, String regiao) {
        this.id = id;
        this.nome = nome;
        this.regiao = regiao;
    }
    Treinador() {   
	this.id = 1;
        this.nome = "James";
	this.regiao = "Unova";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRegiao() {
        return regiao;
    }
}