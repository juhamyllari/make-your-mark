package Menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class NextBookmark extends MenuItem {

    public NextBookmark() {
        super("show the next bookmark");
        addKey("next");
        addKey("x");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.getNext();
    }
    
    
}
