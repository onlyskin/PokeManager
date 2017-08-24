package pokemanager;

public class NullValidator implements Validator<String> {

    public boolean validate(String input) {
        return true;
    }

    public String getValue(String input) {
        return input;
    }

}
