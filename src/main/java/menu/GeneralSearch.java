package menu;

import IO.IO;
import bookmark.BookmarkContainer;
import java.util.ArrayList;
import java.util.List;

public class GeneralSearch extends MenuItem {

    private List<String> searchArea = new ArrayList<>();
    public GeneralSearch() {
        super("search bookmarks");
        addKey("searchg");
        addKey("sg");
        searchArea.add("author");
        searchArea.add("title");
        searchArea.add("description");
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
        
        container.setFilter(searchArea, tags);
        
        if (container.size() == 0) {
            io.print("No bookmarks matching the search criteria.");
            container.dropFilter();
        }
    }
    
    
}
