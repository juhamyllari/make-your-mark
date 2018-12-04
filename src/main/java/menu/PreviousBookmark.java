package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class PreviousBookmark extends MenuItem {

    public PreviousBookmark() {
        super("show the previous bookmark");
        addKey("prev");
        addKey("p");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.getPrevious();
    }
    
    
}
