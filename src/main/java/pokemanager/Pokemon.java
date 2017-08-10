package pokemanager;

public class Pokemon {
    String species;
    Integer height;
    Integer weight;

    public Pokemon(String species, Integer height, Integer weight) {
        this.species = species;
        this.height = height;
        this.weight = weight;
    }

    public String toString() {
        return "Species: "+species+"\nHeight: "+height+"\nWeight: "+weight;
    }
}
