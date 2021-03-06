package bookmark;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single bookmark. The object contains a list of Field objects
 * corresponding to the fields of the bookmark.
 */
public class Bookmark {

    private List<Field> fields;
    private List<Comment> comments;
    private String addedOn;
    private String readOn;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Bookmark(List<Field> entries) {
        this.fields = entries;
        this.comments = new ArrayList<Comment>();
    }

    public Bookmark() {
        this(new ArrayList<Field>());
    }

    public void addComment(String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAddedOn();
        comments.add(comment);
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public boolean isRead() {
        return readOn != null;
    }

    public void markAsRead() {
        LocalDateTime now = LocalDateTime.now();
        this.readOn = formatter.format(now);
    }

    public String getReadOn() {
        return readOn;
    }

    public List<String> getAllSingleFieldNames() {
        return fields.stream()
                .filter(x -> x.isSingleField())
                .map(x -> x.getName())
                .collect(Collectors.toList());
    }

    public boolean containsField(String fieldName) {
        return fieldByName(fieldName) != null;
    }

    public void setListField(String field, List<String> newContent) {
        Field entry = fieldByName(field);
        if (entry == null) {
            entry = new Field(field, newContent);
            this.fields.add(entry);
        } else {
            entry.setData(newContent);
        }
    }

    public void setSingleField(String field, String data) {
        Field entry = fieldByName(field);
        if (entry == null) {
            entry = new Field(field, data);
            this.fields.add(entry);
        } else {
            entry.setData(data);
        }
    }

    /**
     * Add a new element to a list field.
     *
     * @param fieldName
     * @param newElement
     */
    public void addToField(String fieldName, String newElement) {
        Field field = fieldByName(fieldName);
        if (field == null) {
            fields.add(new Field(fieldName, newElement));
        } else {
            field.addToList(newElement);
        }
    }

    private Field fieldByName(String name) {
        return fields.stream()
                .filter(entry -> entry.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public List<String> getListField(String title) {
        Field entry = fieldByName(title);
        if (entry != null) {
            return entry.getList();
        }
        return new ArrayList<>();
    }

    public String getSingleField(String title) {
        Field entry = fieldByName(title);
        if (entry != null) {
            return entry.getFirst();
        }
        return "";
    }

    public List<String> getFieldNames() {
        return fields.stream()
                .map(field -> field.getName())
                .collect(Collectors.toList());
    }

    public List<String> getEmptyFields() {
        return getFieldNames().stream()
                .filter(f -> fieldByName(f).isEmpty())
                .collect(Collectors.toList());
    }

    public boolean titleAuthorOrDescriptionContains(String content) {
        List<String> fieldsToSearch = new ArrayList();
        fieldsToSearch.add("title");
        fieldsToSearch.add("author");
        fieldsToSearch.add("description");
        return fieldsToSearch.stream().anyMatch(field -> fieldContains(field, content));
    }

    public boolean fieldContains(String fieldName, String content) {
        Field entry = fieldByName(fieldName);
        if (entry == null) {
            return false;
        }
        return entry.contains(content);
    }

    public boolean fieldContainsAny(String fieldName, List<String> content) {
        Field entry = fieldByName(fieldName);
        if (entry == null) {
            return false;
        }
        return entry.containsAny(content);
    }

    public boolean fieldIsSingle(String fieldName) {
        Field field = fieldByName(fieldName);
        return field.isSingleField();
    }

    public void setAddedOn() {
        LocalDateTime now = LocalDateTime.now();
        this.addedOn = formatter.format(now);
    }

    public String getAddedOn() {
        return this.addedOn;
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(entry -> entry.toString())
                .collect(Collectors.joining("\n"))
                + "\nAdded on: " + this.addedOn
                + (isRead() ? "\nRead on: " + this.readOn : "")
                + (!comments.isEmpty() ? "\nComments:\n"
                + comments.stream()
                .map(entry -> entry.toString())
                .collect(Collectors.joining("\n")) : "");
    }

    public static Bookmark createBook(String title, String author, String isbn) {
        List<Field> entries = new ArrayList<>();
        entries.add(new Field("Title", title));
        entries.add(new Field("Author", author));
        entries.add(new Field("ISBN", isbn));
        entries.add(new Field("URL", ""));
        entries.add(new Field("Description", ""));
        entries.add(new Field("Tags", new ArrayList<String>()));
        return new Bookmark(entries);
    }

    public static Bookmark createBook() {
        List<Field> entries = new ArrayList<>();
        entries.add(new Field("Title", ""));
        entries.add(new Field("Author", ""));
        entries.add(new Field("ISBN", ""));
        entries.add(new Field("URL", ""));
        entries.add(new Field("Description", ""));
        entries.add(new Field("Tags", new ArrayList<String>()));
        return new Bookmark(entries);
    }

    /**
     * Create a new Bookmark. String fields are initialized with empty strings.
     * List fields are initialized with empty lists.
     */
    public static Bookmark createBookmark() {
        List<Field> entries = new ArrayList<>();
        entries.add(new Field("Title", ""));
        entries.add(new Field("URL", ""));
        entries.add(new Field("Description", ""));
        entries.add(new Field("Author", ""));
        entries.add(new Field("ISBN", ""));
        entries.add(new Field("Tags", new ArrayList<String>()));
        entries.add(new Field("Prerequisite courses", new ArrayList<String>()));
        entries.add(new Field("Related courses", new ArrayList<String>()));
        return new Bookmark(entries);
    }

    public static LocalDateTime parser(Bookmark bm) {
        LocalDateTime dateTime = LocalDateTime.parse(bm.getAddedOn(), formatter);
        return dateTime;
    }

    public static String serializeBookmark(Bookmark bm) {
        Gson gson = new Gson();
        return gson.toJson(bm);
    }

    public static Bookmark deserializeBookmark(String json) {
        Gson gson = new Gson();
        Bookmark bm = gson.fromJson(json, Bookmark.class);
        return bm;
    }

}
