package bookmark;

import java.util.List;
import java.util.stream.Collectors;

public class ListEntry implements Entry {
    
    private String name;
    private List<String> data;

    public ListEntry(String name, List<String> items) {
        this.name = name;
        this.data = items;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setData(List<String> data) {
        this.data = data;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String itemsString = data.stream().collect(Collectors.joining(", "));
        return name + ": " + itemsString;
    }
    
}
