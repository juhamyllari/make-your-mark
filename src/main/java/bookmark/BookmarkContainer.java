package bookmark;

import com.google.gson.Gson;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookmarkContainer {

    private final LinkedList<Bookmark> bookmarks;
    private int index;

    public BookmarkContainer(LinkedList<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
        this.index = 0;
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
            index = 0;
        }
    }

    /**
     * Remove the bookmark from the container.
     *
     * @param bookmark
     */
    public void remove(Bookmark bookmark) {
        if (bookmarks.contains(bookmark)) {
            int indexToRemove = bookmarks.indexOf(bookmark);
            bookmarks.remove(bookmark);
            if (indexToRemove < index) {
                index--;
            } else if (indexToRemove == index) {
                index = 0;
            }
        }
    }

    /**
     * Get the current bookmark.
     *
     * @return bookmarks[index]
     */
    public Bookmark getCurrent() {
        return bookmarks.get(index);
    }

    /**
     * Get the next bookmark. The index in incremented.
     *
     * @return bookmarks[++index]
     */
    public Bookmark getNext() {
        incrementIndex();
        return bookmarks.get(index);
    }

    /**
     * Get the previous bookmark. The index is decremented.
     *
     * @return bookmarks[--index]
     */
    public Bookmark getPrevious() {
        decrementIndex();
        return bookmarks.get(index);
    }

    /**
     * Get the index of the current bookmark. Retrieving a bookmark by its index
     * is not supported. However, we may wish to display the index in a user
     * interface, e.g. "showing bookmark 3 of 42". The method is provided for
     * this purpose.
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    public int size() {
        return bookmarks.size();
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    private void incrementIndex() {
        index++;
        if (index >= bookmarks.size()) {
            index = 0;
        }
    }

    private void decrementIndex() {
        index--;
        if (index < 0) {
            index = bookmarks.size() - 1;
        }
    }
    
    public LinkedList<Bookmark> searchByTags(List<String> tags){
        Function<Stream<Bookmark>,Stream<Bookmark>> f = x -> x;
        for(String tag:tags) f=f.compose(x -> x.filter(y -> y.getListField("tags").contains(tag)));
        return f.apply(bookmarks.stream()).collect(Collectors.toCollection(LinkedList::new));
    }
    
    @Override
    public String toString() {
        return "BookmarkContainer containing:\n" 
                + bookmarks.stream()
                .map(bm -> bm.toString())
                .collect(Collectors.joining("\n--\n"));
    }
    
    public static String serializeBookmarkContainer(BookmarkContainer container) {
        Gson gson = new Gson();
        return gson.toJson(container);
    }
    
    public static BookmarkContainer deserializeBookmarkContainer(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BookmarkContainer.class);
    }
}
