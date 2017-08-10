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

    public String getSpecies() {
        return species;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }
}
