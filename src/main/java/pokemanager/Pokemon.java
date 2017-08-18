package pokemanager;

public class Pokemon {
    private final String species;
    private final String nickname;
    private final Integer level;
    private Integer height;
    private Integer weight;
    
    public Pokemon(String species, String nickname, Integer level,
            Integer height, Integer weight) {
        this.species = species;
        this.nickname = nickname;
        this.level = level;
        this.height = height;
        this.weight = weight;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getHeight() {
        return this.height;
    }

    public Integer getWeight() {
        return this.weight;
    }
}
