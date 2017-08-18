package pokemanager;

public class Pokemon {
    private final String species;
    private final String nickname;
    private final Integer level;
    private Integer height;
    private Integer weight;
    private final String dateCaught;
    private final String locationCaught;
    private Integer currentHp;
    
    public Pokemon(String species, String nickname, Integer level,
            Integer height, Integer weight, String dateCaught,
            String locationCaught, Integer currentHp) {
        this.species = species;
        this.nickname = nickname;
        this.level = level;
        this.height = height;
        this.weight = weight;
        this.dateCaught = dateCaught;
        this.locationCaught = locationCaught;
        this.currentHp = currentHp;
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

    public String getDateCaught() {
        return this.dateCaught;
    }

    public String getLocationCaught() {
        return this.locationCaught;
    }

    public Integer getCurrentHp() {
        return this.currentHp;
    }
}
