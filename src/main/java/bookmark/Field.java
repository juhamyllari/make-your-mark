package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Each Bookmark contains a list of Field objects. The Field objects represent
 the various data items contained in the bookmark, such as "title" and "url".
 The object contains the name of the field and the data held by the field.
 The boolean isSingleField indicates whether the Field is intended to hold
 only one String. If the value is false, the Field can hold a list of Strings.
 If the Field is a String Field, the sole item is retrieved by the getFirst()
 method.
 */
public class Field {

    private String name;
    private List<String> data;
    private final boolean isSingleField;

    public Field(String name, List<String> items) {
        this.name = name;
        this.data = items;
        this.isSingleField = false;
    }

    public Field(String name, String data) {
        this.name = name;
        this.data = new ArrayList<>();
        this.data.add(data);
        this.isSingleField = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
    
    public void addToList(String newItem) {
        this.data.add(newItem);
    }

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

    public boolean isSingleField() {
        return isSingleField;
    }

    public List<String> getList() {
        return data;
    }
}
