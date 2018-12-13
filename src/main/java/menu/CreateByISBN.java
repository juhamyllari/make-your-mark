package menu;

import IO.IO;
import IO.RetrievedVolume;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CreateByISBN extends MenuItem {

    public CreateByISBN() {
        super("create a new bookmark by ISBN");
        addKey("isbn");
        addKey("i");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        String isbn = getISBN(io);
        if (isbn.equals("")) {
            return;
        }
        try {
            RetrievedVolume volume = RetrievedVolume.build(isbn);
            Bookmark bm = Bookmark.createBook();
            bm.setSingleField("Title", volume.getTitle());
            bm.setSingleField("Author", volume.getAuthor());
            bm.setSingleField("ISBN", isbn);
            bm.setSingleField("URL", volume.getURL());
            bm.setSingleField("Description", volume.getDescription());
            List<String> toFill = bm.getEmptyFields();
            for (String field : toFill) {
                Menu.setFieldByUserInput(bm, field, true, io);
            }
            bm.setAddedOn();
            container.add(bm);
            io.print("Bookmark created.");
        } catch (IOException ex) {
            io.nextLine("Failed to read from remote API. (Are you connected to the internet?)\nPress enter to return to the menu.");
        } catch (NullPointerException ex) {
            io.nextLine("No books matching ISBN " + isbn + " were found.\nPress enter to return to the menu.");
        }
    }

    private String getISBN(IO io) {
        String isbn = "";
        boolean done = false;
        while (!done) {
            String rawInput = io.nextLine("Please enter the ISBN (or \"c\" to cancel).");
            if (rawInput.equalsIgnoreCase("c")) {
                return "";
            }
            isbn = rawInput.replaceAll("[^0-9]", "");
            if (isbn.length() == 10 || isbn.length() == 13) {
                done = true;
            } else {
                io.print("A valid ISBN consists of 10 or 13 digits.");
            }
        }
        return isbn;
    }

}
