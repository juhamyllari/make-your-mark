package Menu;

import IO.IO;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;

public class CreateNewBookmark extends MenuItem {

    public CreateNewBookmark() {
        super("create a new bookmark");
        addKey("new");
    }
    
    @Override
    public void execute(BookmarkContainer container, IO io) {
        io.print("Provide the information, please (do not enter any text if you wish to leave the field blank)");
        Bookmark newB = Bookmark.createBookmark();
        for (String fieldName : newB.getFieldNames()) {
            Menu.setFieldByUserInput(newB, fieldName, true, io);
        }
        newB.setAddedOn();
        container.add(newB);
        io.print("Bookmark created.");
    }

}
