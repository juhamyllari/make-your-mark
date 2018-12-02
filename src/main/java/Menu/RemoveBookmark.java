package Menu;

import IO.IO;
import static Menu.Menu.askConfirmation;
import bookmark.BookmarkContainer;

public class RemoveBookmark extends MenuItem {

    public RemoveBookmark() {
        super("remove the current bookmark");
        addKey("remove");
        addKey("r");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        if (askConfirmation(io, "remove the bookmark")) {
            container.remove(container.getCurrent());
        }
    }

}
