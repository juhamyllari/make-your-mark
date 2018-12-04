package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class DropSearchCriteria extends MenuItem {

    public DropSearchCriteria() {
        super("drop the current search filter");
        addKey("drop");
        addKey("d");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.dropFilter();
    }
}
