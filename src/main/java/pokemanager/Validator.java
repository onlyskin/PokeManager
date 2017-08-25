package pokemanager;

public interface Validator<T> {

    boolean validate(String input);

    T getValue(String input);

}
