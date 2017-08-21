package pokemanager;

public class LevelValidator implements IntegerValidator {

    public boolean validate(Integer level) {
        return level > 0 && level < 100;
    }

}
