package Menu;

import IO.FileIO;
import IO.IO;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;
import bookmark.Field;
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
            items.add(new EditBookmarkAllFields());
            if (!container.getCurrent().isRead()) {
                items.add(new MarkAsRead());
            }
            items.add(new RemoveBookmark());
        }
        if (container.size() > 1) {
            items.add(new NextBookmark());
            items.add(new PreviousBookmark());
            items.add(new SearchByTag());
        }
        if (container.hasFilter()) {
            items.add(new DropSearchCriteria());
        }
        if (container.isShowingRead()) {
            items.add(new HideRead());
        } else {
            items.add(new ShowRead());
        }
        items.add(new QuitApplication(this));
    }

    public void printMenu() {
        System.out.println("");
        printStatus();
        System.out.println("");
        printCurrent();
        System.out.println("");
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
        String prompt = "";
        if (initial) {
            prompt = "Give " + fieldName + " one by one for as long as you want; input an empty line to stop.";
        } else {
            prompt = "No " + fieldName.toLowerCase() + " found. Give " + fieldName.toLowerCase() + " one by one for as long as you want; input an empty line to stop.";
        }
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
        routerBook.setSingleField("Title", "An Introduction to GCC");
        routerBook.setSingleField("Author", "Brian Gough");
        routerBook.setSingleField("ISBN", "978-0954161798");
        routerBook.setSingleField("URL", "http://www.network-theory.co.uk/docs/gccintro/");
        routerBook.addToField("Tags", "guide");
        routerBook.addToField("Tags", "compilers");
        routerBook.setAddedOn();
        container.add(routerBook);
        Bookmark fishBook = Bookmark.createBookmark();
        fishBook.setSingleField("Title", "Suomalainen kalaopas");
        fishBook.setSingleField("Author", "Hannu Lehtonen");
        fishBook.setSingleField("ISBN", "951-0-31578-8");
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

    private static void editListField(Bookmark bm, String listField, IO io) {
        List<String> values = bm.getListField(listField);
        if (values.isEmpty()) {
            setListFieldByUserInput(bm, listField, false, io);
        } else {
            String command = "";
            while (true) {
                int indexOf = -2;
                values = bm.getListField(listField);
                io.print(listField + ": (" + values.stream().collect(Collectors.joining(", ")) + ")");
                command = io.nextLine("Type \"(c)hange\" to change values, \"(n)ew\" to add a value or \"(e)xit\" to stop editing.");

                if (command.equals("e") || command.equals("exit")) {
                    break;
                }
                if (command.equals("n") || command.equals("new")) {
                    String newValue = io.nextLine("Provide new value: ");
                    values.add(newValue);
                    io.print("Value added.");
                }
                if (command.equals("c") || command.equals("change")) {
                    while (indexOf < 0) {
                        String value = io.nextLine("Type which value to edit (" + values.stream().collect(Collectors.joining(", ")) + ")");
                        indexOf = values.indexOf(value);
                        if (indexOf == -1) {
                            System.out.println("Value not found. Please try again.");
                        } else {
                            String replacementValue = io.nextLine("Provide replacement value for \"" + value + "\" or type \"(d)elete\" to remove value");
                            if (replacementValue.equals("delete") || replacementValue.equals("d")) {
                                values.remove(indexOf);
                                io.print("Value removed.");
                                if (values.isEmpty()) {
                                    indexOf = 1;
                                }
                            } else {
                                values.set(indexOf, replacementValue);
                                io.print("Value changed.");
                            }
                        }
                    }
                }
            }
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
            editListField(bm, field, io);
        }
    }

    public static void editAll(Bookmark bookmark, IO io) {
        List<Field> changes = new ArrayList<>();
        System.out.println("Type a new entry for each field to change them, leave them blank to make no changes.");
        for (String name : bookmark.getAllSingleFieldNames()) {
            String newValue = io.nextLine(name + " (currently \"" + bookmark.getSingleField(name) + "\"): ");
            if (!newValue.equals(bookmark.getSingleField("")) && !newValue.equals(bookmark.getSingleField(name))) {
                changes.add(new Field(name, newValue));
            }
        }
        System.out.println("Here are the changes you made:");
        for (Field change : changes) {
            System.out.println(change.getName() + ": \"" + bookmark.getSingleField(change.getName()) + "\" -> \"" + change.getFirst() + "\"");
        }
        if (askConfirmation(io, "keep these changes")) {
            for (Field change : changes) {
                bookmark.setSingleField(change.getName(), change.getFirst());
            }
        }
    }

    public static boolean askConfirmation(IO io, String action) {
        boolean confirmed = false;
        String answer;
        while (true) {
            answer = io.nextLine("Are you sure you want to " + action + "? yes/no").toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                confirmed = true;
                break;
            } else if (answer.equals("no") || answer.equals("n")) {
                break;
            }
            io.print("Please answer yes or no");
        }
        return confirmed;
    }
}
