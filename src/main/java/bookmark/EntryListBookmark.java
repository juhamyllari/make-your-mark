package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntryListBookmark implements Bookmark {

    private List<Entry> entries;
    private boolean read;

    public EntryListBookmark(List<Entry> entries) {
        this.entries = entries;
        this.read = false;
    }

    public EntryListBookmark() {
        this(new ArrayList<Entry>());
    }

    public void setStringEntry(String field, String newContent) {
        StringEntry entry = (StringEntry) entryByName(field);
        if (entry == null) {
            entry = new StringEntry(field, newContent);
            this.entries.add(entry);
        } else {
            entry.setName(field);
            entry.setData(newContent);
        }
    }

    public void setListEntry(String field, List<String> newContent) {
        ListEntry entry = (ListEntry) entryByName(field);
        if (entry == null) {
            entry = new ListEntry(field, newContent);
            this.entries.add(entry);
        } else {
            entry.setName(field);
            entry.setData(newContent);
        }
    }

    public String getStringEntry(String field) {
        StringEntry entry = (StringEntry) entryByName(field);
        return entry.getData();
    }

    private Entry entryByName(String name) {
        return entries.stream()
                .filter(entry -> entry.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static EntryListBookmark createBook(String title, String author, String isbn) {
        List<Entry> entries = new ArrayList<>();
        entries.add(new StringEntry("type", "Book"));
        entries.add(new StringEntry("title", title));
        entries.add(new StringEntry("author", author));
        entries.add(new StringEntry("isbn", isbn));
        return new EntryListBookmark(entries);
    }

    @Override
    public List<String> getListEntry(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return entries.stream()
                .map(entry -> entry.toString())
                .collect(Collectors.joining("\n"));
    }

}
