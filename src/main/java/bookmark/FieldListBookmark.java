package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        ListField entry = (ListField) fieldByName(field);
        if (entry == null) {
            entry = new ListField(field, newContent);
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
        entries.add(new ListField("type", "Book"));
        entries.add(new ListField("title", title));
        entries.add(new ListField("author", author));
        entries.add(new ListField("isbn", isbn));
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
