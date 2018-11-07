package model;

/**
 * Classe filho que é um pokemon, mais o Id do treinador a quem ele pertence.
 * @author guiz
 */

// Herança herdada da classe Pokemon
public class PokemonDeTreinador extends Pokemon {
    private int idTreinador;

    public PokemonDeTreinador(int id, int idTreinador, String name, String type1, String type2, 
                              int total, int hp, int attack, int defense, int atk, int spDef, 
                              int speed, int generation, boolean legendary, float height, float weight) {
        
        super(id, name, type1, type2, total, hp, attack, defense,atk, 
              spDef, speed, generation, legendary, height, weight);
        
        this.idTreinador = idTreinador;
    }

    public int getIdTreinador() {
        return idTreinador;
    }
}
