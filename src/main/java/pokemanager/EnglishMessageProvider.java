package pokemanager;

import java.util.Map;
import java.util.HashMap;

public class EnglishMessageProvider implements MessageProvider {
    private Map<String, String> messages = new HashMap<>();
    
    public EnglishMessageProvider() {
        messages.put("badCommand", "Please enter a valid command.\n");
        messages.put("noSpecies", "No species found");
        messages.put("levelInput", "Level:");
        messages.put("speciesInput", "Species:");
        messages.put("nicknameInput", "Nickname:");
        messages.put("locationInput", "Location caught:");
        messages.put("dateInput", "Date caught:");
        messages.put("hpInput", "Current HP:");
        messages.put("storedSuccess", "Stored!");
        messages.put("savedSuccess", "Saved!");
        messages.put("speciesField", "Species");
        messages.put("heightField", "Height");
        messages.put("weightField", "Weight");
        messages.put("searchInput", "Search for:");
        messages.put("onPhrase", "caught on");
        messages.put("atPhrase", "at");
        messages.put("retrieveCommand", "box");
        messages.put("storeCommand", "store");
        messages.put("saveCommand", "save");
        messages.put("exitCommand", "exit");
        messages.put("searchCommand", "search");
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
    };
}
