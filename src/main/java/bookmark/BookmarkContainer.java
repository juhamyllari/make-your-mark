package bookmark;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BookmarkContainer {

    private final LinkedList<Bookmark> bookmarks;
    private LinkedList<Bookmark> filtered;
    private Bookmark current;
    private boolean showingRead;
    private List<SearchCriterion> searchCriterion;
    
    private class SearchCriterion {
        private final String field;
        private final List<String> content;

        private SearchCriterion(String field, List<String> content) {
            this.field = field;
            this.content = content;
        }
    }

    public BookmarkContainer(LinkedList<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
        this.showingRead = false;
        updateFiltered();
        this.current = getFirst();
        this.searchCriterion=new ArrayList<>();
    }

    public BookmarkContainer() {
        this(new LinkedList<>());
    }

    /**
     * Add a new bookmark to the container. If the bookmark is already present
     * in the container it will not be added again.
     *
     * @param bookmark
     */
    public void add(Bookmark bookmark) {
        if (!bookmarks.contains(bookmark)) {
            bookmarks.addFirst(bookmark);
            current = bookmark;
            updateFiltered();
        }
    }

    /**
     * Remove the bookmark from the container.
     *
     * @param bookmark
     */
    public void remove(Bookmark bookmark) {
        if (!bookmarks.contains(bookmark)) {
            return;
        }
        bookmarks.remove(bookmark);
        updateFiltered();
    }

    /**
     * Get the current bookmark.
     *
     * @return bookmarks[index]
     */
    public Bookmark getCurrent() {
        return current;
    }

    /**
     * Get the next bookmark. The index in incremented.
     *
     * @return bookmarks[++index]
     */
    public Bookmark getNext() {
        if (filtered.isEmpty()) {
            return null;
        }
        if (filtered.size() == 1) {
            return filtered.getFirst();
        }
        int currentIndex = filtered.indexOf(current);
        if (currentIndex == filtered.size() - 1) {
            current = getFirst();
            return current;
        }
        current = filtered.get(currentIndex + 1);
        return current;
    }

    private Bookmark getFirst() {
        if (filtered.isEmpty()) {
            return null;
        }
        return filtered.getFirst();
    }

    /**
     * Get the previous bookmark. The index is decremented.
     *
     * @return bookmarks[--index]
     */
    public Bookmark getPrevious() {
        if (filtered.isEmpty()) {
            return null;
        }
        if (filtered.size() == 1) {
            return filtered.getFirst();
        }
        int currentIndex = filtered.indexOf(current);
        if (currentIndex == 0) {
            current = filtered.get(filtered.size() - 1);
            return current;
        }
        current = filtered.get(currentIndex - 1);
        return current;
    }

    /**
     * Get the index of the current bookmark, e.g. to display the index in a
     * user interface: "showing bookmark 3 of 42".
     *
     * @return index
     */
    public int getIndex() {
        return filtered.indexOf(current);
    }

    public int size() {
        return filtered.size();
    }
    
    public void markCurrentAsRead() {
        current.markAsRead();
        updateFiltered();
    }
    
    public int unfilteredSize() {
        return bookmarks.size();
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

//    public LinkedList<Bookmark> searchByTagsAND(List<String> tags){
//        Function<Stream<Bookmark>,Stream<Bookmark>> f = x -> x;
//        for(String tag:tags) f=f.compose(x -> x.filter(y -> y.getListField("tags").contains(tag)));
//        return f.apply(bookmarks.stream()).collect(Collectors.toCollection(LinkedList::new));
//    }
    
    public LinkedList<Bookmark> searchByTagsOR(List<String> tags) {
        return bookmarks.stream().filter(x -> tags.stream().anyMatch(y -> x.getListField("tags").contains(y))).collect(Collectors.toCollection(LinkedList::new));
    }

    public void setFilterField(String fieldName, List<String> content) {
        this.searchCriterion.add(new SearchCriterion(fieldName, content));
        updateFiltered();
    }
    
    public void setFilter(List<String> fieldNames, List<String> content) {
        for(String fieldName:fieldNames){
            this.searchCriterion.add(new SearchCriterion(fieldName, content));
        }
        updateFiltered();
    }
    
    public void dropFilter() {
        this.searchCriterion = null;
        updateFiltered();
    }
    
    public boolean hasFilter() {
        return searchCriterion != null;
    }

    @Override
    public String toString() {
        return "BookmarkContainer containing:\n"
                + bookmarks.stream()
                        .map(bm -> bm.toString())
                        .collect(Collectors.joining("\n--\n"));
    }

    public String serialize() {
        this.dropFilter();
        this.filtered = null;
        Gson gson = new Gson();
        this.updateFiltered();
        return gson.toJson(this);
    }

    public static BookmarkContainer deserializeBookmarkContainer(String json) {
        Gson gson = new Gson();
        BookmarkContainer container = gson.fromJson(json, BookmarkContainer.class);
        container.updateFiltered();
        return container;
    }

    private void updateFiltered() {
        if(searchCriterion.isEmpty()){
            filtered = bookmarks.stream()
                .filter(bm -> showingRead || !bm.isRead())
                .filter(bm -> searchCriterion.stream().anyMatch(sc -> bm.fieldContainsAny(sc.field, sc.content)))
                .collect(Collectors.toCollection(LinkedList::new));
            if (!filtered.contains(current)) {
                current = getNext();
            }
        }else{
            filtered = bookmarks.stream()
                .filter(bm -> showingRead || !bm.isRead())
                .collect(Collectors.toCollection(LinkedList::new));
        }
    }

    public void resetIndex() {
        current = getFirst();
    }

    public boolean isShowingRead() {
        return showingRead;
    }
    
    public void setShowingRead(boolean show) {
        showingRead = show;
        updateFiltered();
    }
}
