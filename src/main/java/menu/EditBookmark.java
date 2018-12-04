package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class EditBookmark extends MenuItem {

    public EditBookmark() {
        super("edit the current bookmark");
        addKey("edit");
        addKey("e");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        Menu.edit(container.getCurrent(), io);
    }
    
    
}
