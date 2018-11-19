package bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarkContainer {

    private final List<Lukuvinkki> bookmarks;
    private int index;

    public BookmarkContainer(List<Lukuvinkki> bookmarks) {
        this.bookmarks = bookmarks;
        this.index = 0;
    }

    public BookmarkContainer() {
        this(new ArrayList<>());
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
        if (bookmarks.contains(bookmark)) {
            int indexToRemove = bookmarks.indexOf(bookmark);
            bookmarks.remove(bookmark);
            if (indexToRemove < index) {
                index--;
            }
        }
    }

    /**
     * Get the current bookmark.
     *
     * @return bookmarks[index]
     */
    public Lukuvinkki getCurrent() {
        return bookmarks.get(index);
    }

    /**
     * Get the next bookmark. The index in incremented.
     *
     * @return bookmarks[++index]
     */
    public Lukuvinkki getNext() {
        incrementIndex();
        return bookmarks.get(index);
    }

    /**
     * Get the previous bookmark. The index is decremented.
     *
     * @return bookmarks[--index]
     */
    public Lukuvinkki getPrevious() {
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

    public List<Lukuvinkki> getBookmarks() {
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
}
