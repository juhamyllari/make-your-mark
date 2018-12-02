package Menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class ShowRead extends MenuItem {

    public ShowRead() {
        super("show/hide (marked as) read bookmarks");
        addKey("show");
        addKey("sh");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.setShowingRead(!container.isShowingRead());
    }
}
