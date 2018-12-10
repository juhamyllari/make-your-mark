package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class AddComment extends MenuItem {

    public AddComment() {
        super("add a new comment to the current bookmark");
        addKey("comment");
        addKey("c");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        String prompt = "Give comments one by one for as long as you want; input an empty line to stop.";
        while (true) {
            String newO = io.nextLine(prompt);
            if (newO.trim().equals("")) {
                break;
            }
            container.getCurrent().addComment(newO);
        }
    }
}
