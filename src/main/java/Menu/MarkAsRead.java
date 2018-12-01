package Menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class MarkAsRead extends MenuItem {

    public MarkAsRead() {
        super("mark bookmark as read");
        addKey("mark");
        addKey("m");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.markCurrentAsRead();
    }
    
}
