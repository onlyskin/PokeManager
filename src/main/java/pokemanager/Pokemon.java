package pokemanager;

public class Pokemon {
    private final String species;
    private final String nickname;
    private final Integer level;
    
    public Pokemon(String species, String nickname, Integer level) {
        this.species = species;
        this.nickname = nickname;
        this.level = level;
    }

    public String toJSONString() {
        return "{\"level\":" + getLevel().toString() +
            ",\"species\":\"" + getSpecies() +
            "\",\"nickname\":\"" + getNickname() + "\"}";
    }

    public String prettyString() {
        return getNickname() + " - lv." + getLevel().toString() + " " + getSpecies();
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
}
