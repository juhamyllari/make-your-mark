package Menu;

import IO.FileIO;
import IO.IO;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mainApp.App;

public class Menu {

    public static final String BOOKMARK_FILE = "saved_bookmarks.txt";

    private BookmarkContainer container;
    private IO io;
    private List<MenuItem> items;
    private boolean exiting;

    public Menu(BookmarkContainer container, IO io) {
        this.container = container;
        this.io = io;
        this.items = new ArrayList<>();
        this.exiting = false;
    }

    private void createItems() {
        items = new ArrayList<>();
        items.add(new CreateNewBookmark());
        items.add(new CreateSamples());
        if (container.getCurrent() != null) {
            items.add(new EditBookmark());
            items.add(new MarkAsRead());
        }
        if (container.size() > 1) {
            items.add(new NextBookmark());
            items.add(new PreviousBookmark());
            items.add(new SearchByTag());
        }
        if (container.hasFilter()) {
            items.add(new DropSearchCriteria());
        }
        items.add(new ShowRead());
        items.add(new QuitApplication(this));
    }

    public void printMenu() {
        printStatus();
        printCurrent();
        createItems();
        printItems();
    }

    public boolean isExiting() {
        return exiting;
    }

    public void setExiting(boolean exiting) {
        this.exiting = exiting;
    }

    private void printStatus() {
        String readStatus = container.isShowingRead() ? "" : " unread";
        if (container.hasFilter()) {
            io.print(container.size()
                    + readStatus
                    + " bookmarks match your search criteria.");
        } else {
            io.print("You have "
                    + container.size()
                    + readStatus
                    + (container.size() == 1 ? " bookmark." : " bookmarks.")
            );
        }
    }

    private void printCurrent() {
        if (container.getCurrent() != null) {
            io.print("Showing bookmark " + (container.getIndex() + 1) + "/" + container.size());
            io.print(container.getCurrent().toString());
        } else {
            io.print("Nothing to show");
        }
    }

    private static void setListFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        String prompt = "Give " + fieldName + " one by one for as long as you want; input an empty line to stop.";
        List<String> items = addList(io, prompt);
        bm.setListField(fieldName, items);
    }

    private static void setSingleFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        String prompt = initial ? fieldName + ":" : "new " + fieldName + ":";
        String newValue = io.nextLine(prompt);
        bm.setSingleField(fieldName, newValue);
    }

    public static void setFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        if (bm.fieldIsSingle(fieldName)) {
            setSingleFieldByUserInput(bm, fieldName, initial, io);
        } else {
            setListFieldByUserInput(bm, fieldName, initial, io);
        }
    }

    private static ArrayList<String> addList(IO io, String prompt) {
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String newO = io.nextLine(prompt);
            if (newO.trim().equals("")) {
                break;
            }
            list.add(newO);
        }
        return list;
    }

    public static boolean containerHasChanged(BookmarkContainer container) {
        BookmarkContainer savedContainer = FileIO.loadContainerFromFile(BOOKMARK_FILE);
        if (App.treatFileAsMissing || savedContainer == null) {
            return container.size() != 0;
        }
        String newContainerJSON = container.serialize();
        String savedContainerJSON = savedContainer.serialize();
        return !savedContainerJSON.equals(newContainerJSON);
    }

    private void printItems() {
        items.forEach(item -> io.print(item.toString()));
    }

    public static void createSamples(BookmarkContainer container) {
        Bookmark routerBook = Bookmark.createBookmark();
        routerBook.setSingleField("Title", "Reitittimet 1992-1996");
        routerBook.setSingleField("Author", "Koodi Kalevi");
        routerBook.setSingleField("ISBN", "43289-23432");
        routerBook.addToField("Tags", "guide");
        routerBook.setAddedOn();
        container.add(routerBook);
        Bookmark fishBook = Bookmark.createBookmark();
        fishBook.setSingleField("Title", "Kalaopas");
        fishBook.setSingleField("Author", "Kimmo Kala");
        fishBook.setSingleField("ISBN", "8493-33");
        fishBook.addToField("Tags", "hobbies");
        fishBook.addToField("Tags", "fishing");
        fishBook.addToField("Tags", "guide");
        fishBook.setAddedOn();
        container.add(fishBook);
    }

    public void getCommandAndExecute() {
        MenuItem item;
        while (true) {
            String str = io.nextLine("Enter command.");
            item = items.stream()
                    .filter(mi -> mi.getKeys().contains(str))
                    .findFirst()
                    .orElse(null);
            if (item != null) {
                break;
            } else {
                io.print("Unknown command. Please try again.");
            }
        }
        item.execute(container, io);
    }

    private static void editSingleField(Bookmark bm, String field, IO io) {
        String newEntry = io.nextLine("Current " + field + ": " + bm.getSingleField(field) + ". Set a new " + field + " or type the current " + field + " to remove it.");
        if (newEntry.equals(bm.getSingleField(field))) {
            bm.setSingleField(field, "");
            io.print(field + " removed.");
        } else if (newEntry.trim().equals("")) {
            io.print("No change made.");
        } else {
            bm.setSingleField(field, newEntry);
            io.print("New " + field + " set.");
        }
    }

    //    private static void editListField(Bookmark bm, String field, IO io) {
    //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //    }
    // List editing unfinished: User can only replace or remove all elements of
    // a list field with one value.
    public static void edit(Bookmark bm, IO io) {
        List<String> allFields = bm.getFieldNames();
        String field = io.nextLine("Type which field to edit (" + allFields.stream().collect(Collectors.joining(", ")) + ") or \"exit\" to stop editing.");
        if (field.equals("exit")) {
            return;
        }
        if (!bm.containsField(field)) {
            io.print("Invalid field.");
            edit(bm, io);
            return;
        }
        if (bm.fieldIsSingle(field)) {
            editSingleField(bm, field, io);
        } else {
            //editListField(bm, field, io);
        }
    }
}
