package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class CreateSamples extends MenuItem {

    public CreateSamples() {
        super("create sample bookmarks");
        addKey("samples");
        addKey("s");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        Menu.createSamples(container);
    }
    
    
    
}
