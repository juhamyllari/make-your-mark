package menu;

import IO.IO;
import IO.RetrievedVolume;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateByISBN extends MenuItem {

    public CreateByISBN() {
        super("create a new bookmark by ISBN");
        addKey("isbn");
        addKey("i");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        String isbn = getISBN(io);
        try {
            RetrievedVolume volume = RetrievedVolume.build(isbn);
            Bookmark bm = new Bookmark();
            bm.setSingleField("Title", volume.getTitle());
            bm.setSingleField("Author", volume.getAuthor());
            bm.setAddedOn();
            container.add(bm);
        } catch (IOException ex) {
            Logger.getLogger(CreateByISBN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getISBN(IO io) {
        // TODO: format string to remove hyphens etc.
        String isbn = io.nextLine("Please enter ISBN.");
        return isbn;
    }

}
