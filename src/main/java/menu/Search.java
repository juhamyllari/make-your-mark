
package menu;

import IO.IO;
import bookmark.BookmarkContainer;

public class Search extends MenuItem {
    
    public Search() {
        super("search bookmarks by title, author or description");
        addKey("search");
        addKey("se");
    }
    
    @Override
    public void execute(BookmarkContainer container, IO io) {
        String filterString = io.nextLine("Give keyword to search from title, author and description:");
        container.setFilter(filterString.trim());

        if (container.size() == 0) {
            io.nextLine("No bookmarks matching the search criteria.\nPress enter to return to the menu.");
            container.dropFilter();
        }
    }
    
}
