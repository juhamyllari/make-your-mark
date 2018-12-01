package mainApp;

import IO.*;
import Menu.Menu;
import bookmark.BookmarkContainer;
import bookmark.Bookmark;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {


    public static void main(String[] args) {
        IO io = new ConsoleIO();
        run(io, true);
    }

    public static void run(IO io, boolean loadBookmarks) {
        Menu.treatFileAsMissing = !loadBookmarks;
        BookmarkContainer container;
        if (loadBookmarks && Files.exists(Paths.get(Menu.BOOKMARK_FILE))) {
            container = FileIO.loadContainerFromFile(Menu.BOOKMARK_FILE);
        } else {
            container = new BookmarkContainer();
        }
        while (true) {
            
            Menu menu = new Menu(container, io);
            menu.printMenu();
            menu.getCommandAndExecute();
//            String command = io.nextLine("Type \"(n)ew\" to create a bookmark, \"(b)rowse\" to browse the bookmarks or \"(e)xit\" to quit the application.");
//
//            if (command.equals("new") || command.equals("n")) {
//                createNew(container, io);
//            } else if (command.equals("browse") || command.equals("b")) {
//                if (container.size() == 0) {
//                    io.print("No bookmarks");
//                } else {
//                    browse(container, io);
//                }
//            } else if (command.equals("exit") || command.equals("e")) {
//                break;
//            } else if (command.equals("samples") || command.equals("s")) {
//                Menu.createSamples(container);
//            } else {
//                io.print("Invalid command");
//            }
        }
    }

    public static void createSampleSaveFile(FileIO fio) {
        BookmarkContainer bmc = new BookmarkContainer();
        Menu.createSamples(bmc);
        fio.saveContainerToFile(bmc, Menu.BOOKMARK_FILE);
    }


    private static void browse(BookmarkContainer container, IO io) {
        if (container.getIndex() > 0) {
            String resume = io.nextLine("Type \"first\" to start from the beginning (last added) or leave empty to continue form your last browsed bookmark.");
            if (resume.equals("first")) container.resetIndex();
        }
        
        while (true) {
            io.print(container.getCurrent().toString());
            io.print((container.getIndex() + 1) + "/" + container.size());
            String command = io.nextLine("Type \"next\" to see the next bookmark, \"prev\" to see the previous bookmark, \"search\" to search for bookmarks, \"edit\" to edit the current one or \"exit\" to stop browsing bookmarks.");
            if (command.equals("next")) {
                container.getNext();
            } else if (command.equals("prev")) {
                container.getPrevious();
            } else if (command.equals("search")) {
                search(io, container);
            } else if (command.equals("edit")) {
                Menu.edit(container.getCurrent(), io);
            } else if (command.equals("exit")) {
                break;
            } else {
                io.print("Invalid command.");
            }
        }
    }

    private static void search(IO io, BookmarkContainer container) {
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
        } else {
            browseList(container, io);
        }
    }

    private static void browseList(BookmarkContainer container, IO io) {
        while (true) {
            io.print(container.getCurrent().getSingleField("title") + " " + (container.getIndex() + 1) + "/" + container.size());
            String command = io.nextLine("Type \"next\" to see the next bookmark, \"prev\" to see the previous bookmark, \"show\" to show more information on the current one, or \"exit\" to stop browsing search results.");
            if (command.equals("next")) {
                container.getNext();
            } else if (command.equals("prev")) {
                container.getPrevious();
            } else if (command.equals("show")) {
                io.print(container.getCurrent().toString());
            } else if (command.equals("exit")) {
                break;
            } else {
                io.print("Invalid command.");
            }
        }
    }

}
