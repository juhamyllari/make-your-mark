
package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class Search extends MenuItem {
    
    public Search() {
        super("search bookmarks by title, author or description");
        addKey("tadsearch");
        addKey("ts");
    }
    
    @Override
    public void execute(BookmarkContainer container, IO io) {
        String filterString = io.nextLine("Give keyword to search from title, author and description:");
        container.setFilter(filterString.trim());

        if (container.size() == 0) {
            io.print("No bookmarks matching the search criteria.");
            container.dropFilter();
        }
    }
    
}
