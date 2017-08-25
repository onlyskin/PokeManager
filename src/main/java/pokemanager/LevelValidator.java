package pokemanager;

public class LevelValidator implements Validator<Integer> {

    private Validator[] childValidators = new Validator[]{
        new IntegerValidator(),
        new RangeValidator(1, 99)
    };

    public boolean validate(String input) {
        for (Validator validator : childValidators) {
            if (!validator.validate(input)) return false;
        }
        return true;
    }

    public Integer getValue(String input) {
        return Integer.parseInt(input);
    }

}
