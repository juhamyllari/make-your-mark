package bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarkContainer {

    private final List<Lukuvinkki> bookmarks;

    public BookmarkContainer(List<Lukuvinkki> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public BookmarkContainer() {
        this.bookmarks = new ArrayList<>();
    }
    
    public List<Lukuvinkki> getBookmarks() {
        return bookmarks;
    }

    /**
     * Add a new bookmark to the container. If the bookmark is already present
     * in the container it will not be added again.
     *
     * @param bookmark
     */
    public void add(Lukuvinkki bookmark) {
        if (!bookmarks.contains(bookmark)) {
            bookmarks.add(bookmark);
        }
    }

    /**
     * Remove the bookmark from the container.
     *
     * @param bookmark
     */
    public void remove(Lukuvinkki bookmark) {
        bookmarks.remove(bookmark);
    }

    public int size() {
        return bookmarks.size();
    }
}
