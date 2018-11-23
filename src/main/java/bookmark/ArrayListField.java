package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Field implemented using an ArrayList.
 * The object contains the name of the field and the data held by the field.
 * The boolean isStringField indicates whether the Field is intended to hold
 * only one String. If the value is false, the Field can hold a list of Strings.
 * If the Field is a String Field, the sole item is retrieved by the getFirst()
 * method.
 */
public class ArrayListField implements Field {
    
    private String name;
    private List<String> data;
    private boolean isStringField;

    public ArrayListField(String name, List<String> items) {
        this.name = name;
        this.data = items;
        this.isStringField = false;
    }
    
    public ArrayListField(String name, String data) {
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

    @Override
    public List<String> getList() {
        return data;
    }
    
}
