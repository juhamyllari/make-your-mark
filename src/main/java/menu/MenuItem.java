package menu;

import IO.IO;
import bookmark.BookmarkContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MenuItem {

    private String description;
    private List<String> keys;

    public MenuItem(String item) {
        this.description = item;
        this.keys = new ArrayList<>();
    }

    public void addKey(String key) {
        this.keys.add(key);
    }

    public List<String> getKeys() {
        return keys;
    }
    
    public void execute(BookmarkContainer container, IO io) {
        
    }

    @Override
    public String toString() {
        return "type " 
                + keys.stream().collect(Collectors.joining("\" or \"", "\"", "\"")) 
                + " to " 
                + description;
    }
    
    

}
