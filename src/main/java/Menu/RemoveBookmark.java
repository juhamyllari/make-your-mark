package Menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class RemoveBookmark extends MenuItem {
    
    public RemoveBookmark() {
        super("remove the current bookmark");
        addKey("remove");
        addKey("r");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.remove(container.getCurrent());
    }
}
