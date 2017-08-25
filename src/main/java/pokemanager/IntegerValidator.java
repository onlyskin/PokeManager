package pokemanager;

public class IntegerValidator implements Validator<Integer> {

    public boolean validate(String input) {
        Integer level = null;
        try {
            level = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Integer getValue(String input) {
        return Integer.parseInt(input);
    }

}
