package Menu;

import IO.FileIO;
import IO.IO;
import bookmark.BookmarkContainer;
import mainApp.App;

public class QuitApplication extends MenuItem {

    private Menu menu;

    QuitApplication(Menu menu) {
        super("quit the application");
        addKey("quit");
        addKey("q");
        this.menu = menu;
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        container.resetIndex();
        if (Menu.containerHasChanged(container)) {
            String save = io.nextLine("Save changes to file? yes/no");
            if (save.toLowerCase().equals("yes") || save.toLowerCase().equals("y")) {
                if (FileIO.saveContainerToFile(container, Menu.BOOKMARK_FILE)) {
                    io.print("Saved.");
                }
            } else {
                io.print("Quitting without saving.");
            }
        }
        menu.setExiting(true);
    }

}
