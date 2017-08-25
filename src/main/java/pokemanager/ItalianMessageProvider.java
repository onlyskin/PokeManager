package pokemanager;

import java.util.Map;
import java.util.HashMap;

public class ItalianMessageProvider implements MessageProvider {
    private Map<String, String> messages = new HashMap<>();
    
    public ItalianMessageProvider() {
        messages.put("badCommand", "Scegliere un istruzione valida.\n");
        messages.put("noSpecies", "Nessuna specie trovata.");
        messages.put("levelInput", "Livello:");
        messages.put("speciesInput", "Specie:");
        messages.put("nicknameInput", "Nomignolo:");
        messages.put("locationInput", "Luogo di caccia:");
        messages.put("dateInput", "Data di caccia:");
        messages.put("hpInput", "Attuale Hp:");
        messages.put("storedSuccess", "Depositato!");
        messages.put("savedSuccess", "Salvato!");
        messages.put("speciesField", "Specie");
        messages.put("heightField", "Altezza");
        messages.put("weightField", "Peso");
        messages.put("searchInput", "Specie da ricerca:");
        messages.put("onPhrase", "cacciato");
        messages.put("atPhrase", "a");
        messages.put("retrieveCommand", "visionare");
        messages.put("storeCommand", "depositare");
        messages.put("saveCommand", "salvare");
        messages.put("exitCommand", "chiudere");
        messages.put("searchCommand", "ricercare");
    }

    public String startupMessage() {
        return "Commands:" +
            "\n'" + getMessage("retrieveCommand")  + "' to see stored Pokemon" +
            "\n'" + getMessage("storeCommand") + "' to store a Pokemon" +
            "\n'" + getMessage("saveCommand") + "' to save your stored Pokemon for next time" +
            "\n'" + getMessage("searchCommand") + "' to search the Pokedex";
    }

    public String getMessage(String id) {
        return messages.get(id);
    }
}
