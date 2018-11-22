package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A bookmark implemented as a list of Field objects.
 * To create an empty bookmark call the factory method createBookmark.
 */
public class FieldListBookmark implements Bookmark {

    private List<Field> fields;
    private boolean read;

    public FieldListBookmark(List<Field> entries) {
        this.fields = entries;
        this.read = false;
    }

    public FieldListBookmark() {
        this(new ArrayList<Field>());
    }

    public void setField(String field, List<String> newContent) {
        ArrayListField entry = (ArrayListField) fieldByName(field);
        if (entry == null) {
            entry = new ArrayListField(field, newContent);
            this.fields.add(entry);
        } else {
            entry.setName(field);
            entry.setData(newContent);
        }
    }

    @Override
    public void setField(String name, String data) {
        List<String> list = new ArrayList<>();
        list.add(data);
        setField(name, list);
    }

    private Field fieldByName(String name) {
        return fields.stream()
                .filter(entry -> entry.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static FieldListBookmark createBook(String title, String author, String isbn) {
        List<Field> entries = new ArrayList<>();
        entries.add(new ArrayListField("type", "Book"));
        entries.add(new ArrayListField("title", title));
        entries.add(new ArrayListField("author", author));
        entries.add(new ArrayListField("isbn", isbn));
        return new FieldListBookmark(entries);
    }

    public static FieldListBookmark createBookmark() {
        List<Field> entries = new ArrayList<>();
        entries.add(new ArrayListField("title", ""));
        entries.add(new ArrayListField("url", ""));
        entries.add(new ArrayListField("description", ""));
        entries.add(new ArrayListField("author", ""));
        entries.add(new ArrayListField("comment", ""));
        entries.add(new ArrayListField("isbn", ""));
        entries.add(new ArrayListField("tags", ""));
        entries.add(new ArrayListField("prerequisite courses", ""));
        entries.add(new ArrayListField("related courses", ""));
        return new FieldListBookmark(entries);
    }

    @Override
    public List<String> getListField(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(entry -> entry.toString())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getStringField(String title) {
        Field entry = fieldByName(title);
        return entry.getFirst();
    }

    @Override
    public List<String> getFieldNames() {
        return fields.stream()
                .map(field -> field.getName())
                .collect(Collectors.toList());
    }

    @Override
    public boolean fieldIsString(String fieldName) {
        Field field = fieldByName(fieldName);
        return field.isStringField();
    }

}
