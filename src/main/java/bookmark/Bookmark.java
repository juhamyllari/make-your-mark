package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a single bookmark.
 * The object contains a list of Field objects corresponding to the fields
 * of the bookmark.
 */
public class Bookmark {

    private List<Field> fields;
    private boolean read;

    public Bookmark(List<Field> entries) {
        this.fields = entries;
        this.read = false;
    }

    public Bookmark() {
        this(new ArrayList<Field>());
    }

    public void setField(String field, List<String> newContent) {
        Field entry = fieldByName(field);
        if (entry == null) {
            entry = new Field(field, newContent);
            this.fields.add(entry);
        } else {
            entry.setName(field);
            entry.setData(newContent);
        }
    }

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

    public List<String> getListField(String title) {
        Field entry = fieldByName(title);
        return entry.getList();
    }

    public String getStringField(String title) {
        Field entry = fieldByName(title);
        return entry.getFirst();
    }

    public List<String> getFieldNames() {
        return fields.stream()
                .map(field -> field.getName())
                .collect(Collectors.toList());
    }

    public boolean fieldIsString(String fieldName) {
        Field field = fieldByName(fieldName);
        return field.isStringField();
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(entry -> entry.toString())
                .collect(Collectors.joining("\n"));
    }

    public static Bookmark createBook(String title, String author, String isbn) {
        List<Field> entries = new ArrayList<>();
        entries.add(new Field("type", "Book"));
        entries.add(new Field("title", title));
        entries.add(new Field("author", author));
        entries.add(new Field("isbn", isbn));
        return new Bookmark(entries);
    }

    /**
     * Create a new Bookmark. String fields are initialized with empty strings.
     * List fields are initialized with empty lists.
     */
    public static Bookmark createBookmark() {
        List<Field> entries = new ArrayList<>();
        entries.add(new Field("title", ""));
        entries.add(new Field("url", ""));
        entries.add(new Field("description", ""));
        entries.add(new Field("author", ""));
        entries.add(new Field("comment", ""));
        entries.add(new Field("isbn", ""));
        entries.add(new Field("tags", new ArrayList<String>()));
        entries.add(new Field("prerequisite courses", new ArrayList<String>()));
        entries.add(new Field("related courses", new ArrayList<String>()));
        return new Bookmark(entries);
    }

}
