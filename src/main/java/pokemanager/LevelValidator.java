package pokemanager;

public class LevelValidator implements Validator<Integer> {

    public boolean validate(String input) {
        Integer level = null;
        try {
            level = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return level > 0 && level < 100;
    }

    public Integer getValue(String input) {
        return Integer.parseInt(input);
    }

}
