package pokemanager;

public class RangeValidator implements Validator<Integer> {

    public boolean validate(String input) throws NumberFormatException {
        int level = Integer.parseInt(input);
        return level > 0 && level < 100;
    }

    public Integer getValue(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }
}
