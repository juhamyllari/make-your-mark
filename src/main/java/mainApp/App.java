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

    public static boolean treatFileAsMissing = false; // for some Cucumber tests
    public static boolean quit = false;

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        run(io, true);
    }

    public static void run(IO io, boolean loadBookmarks) {
        treatFileAsMissing = !loadBookmarks;
        BookmarkContainer container;
        if (loadBookmarks && Files.exists(Paths.get(Menu.BOOKMARK_FILE))) {
            container = FileIO.loadContainerFromFile(Menu.BOOKMARK_FILE);
        } else {
            container = new BookmarkContainer();
        }
        while (!quit) {
            Menu menu = new Menu(container, io);
            menu.printMenu();
            menu.getCommandAndExecute();
        }
    }

    public static void createSampleSaveFile(FileIO fio) {
        BookmarkContainer bmc = new BookmarkContainer();
        Menu.createSamples(bmc);
        fio.saveContainerToFile(bmc, Menu.BOOKMARK_FILE);
    }

}
