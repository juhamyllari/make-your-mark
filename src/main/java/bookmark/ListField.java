package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListField implements Field {
    
    private String name;
    private List<String> data;
    private boolean isStringField;

    public ListField(String name, List<String> items) {
        this.name = name;
        this.data = items;
        this.isStringField = false;
    }
    
    public ListField(String name, String data) {
        this.name = name;
        this.data = new ArrayList<>();
        this.data.add(data);
        this.isStringField = true;
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
    
    public String getFirst() {
        return data.get(0);
    }

    @Override
    public String toString() {
        String itemsString = data.stream().collect(Collectors.joining(", "));
        return name + ": " + itemsString;
    }

    @Override
    public boolean isStringField() {
        return isStringField;
    }
    
}
