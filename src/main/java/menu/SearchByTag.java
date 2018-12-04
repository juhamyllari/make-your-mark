package menu;

import IO.IO;
import bookmark.BookmarkContainer;
import java.util.ArrayList;

public class SearchByTag extends MenuItem {

    public SearchByTag() {
        super("search bookmarks by tag");
        addKey("search");
        addKey("se");
    }

    @Override
    public void execute(BookmarkContainer container, IO io) {
        ArrayList<String> tags = new ArrayList<>();
        while (true) {
            String newTag = io.nextLine("Give tags one by one for as long as you want; input an empty line to stop.");
            if (newTag.trim().equals("")) {
                break;
            }
            tags.add(newTag);
        }
        
        container.setFilter("Tags", tags);
        
        if (container.size() == 0) {
            io.print("No bookmarks matching the search criteria.");
            container.dropFilter();
        }
    }
    
    
}
