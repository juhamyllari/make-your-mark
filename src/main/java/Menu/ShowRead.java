package Menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class ShowRead extends MenuItem {

    public ShowRead() {
        super("show read bookmarks");
        addKey("show");
        addKey("sh");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.setShowingRead(true);
    }
}
