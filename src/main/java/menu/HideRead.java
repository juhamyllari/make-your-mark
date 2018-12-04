package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class HideRead extends MenuItem {

    public HideRead() {
        super("hide read bookmarks");
        addKey("hide");
        addKey("h");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.setShowingRead(false);
    }

}
