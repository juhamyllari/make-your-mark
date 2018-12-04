package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class EditBookmarkAllFields extends MenuItem {
    public EditBookmarkAllFields() {
        super("edit the current bookmark");
        addKey("editall");
        addKey("ea");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        Menu.editAll(container.getCurrent(), io);
    }
}
