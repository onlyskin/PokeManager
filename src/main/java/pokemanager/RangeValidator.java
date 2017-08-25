package pokemanager;

public class RangeValidator implements Validator<Integer> {
    private final int low;
    private final int hi;

    public RangeValidator(int low, int hi) {
        this.low = low;
        this.hi = hi;
    }

    public boolean validate(String input) throws NumberFormatException {
        int level = Integer.parseInt(input);
        return level >= low && level <= hi;
    }

    public Integer getValue(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }
}
