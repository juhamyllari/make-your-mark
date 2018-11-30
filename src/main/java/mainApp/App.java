package mainApp;

import IO.*;
import bookmark.BookmarkContainer;
import bookmark.Bookmark;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static final String BOOKMARK_FILE = "saved_bookmarks.txt";
    private static boolean treatFileAsMissing = false;  // for some Cucumber tests

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        run(io, true);
    }

    public static void run(IO io, boolean loadBookmarks) {
        treatFileAsMissing = !loadBookmarks;
        BookmarkContainer container;
        if (loadBookmarks && Files.exists(Paths.get(BOOKMARK_FILE))) {
            container = FileIO.loadContainerFromFile(BOOKMARK_FILE);
        } else {
            container = new BookmarkContainer();
        }
        while (true) {
            String command = io.nextLine("Type \"(n)ew\" to create a bookmark, \"(b)rowse\" to browse the bookmarks or \"(e)xit\" to quit the application.");

            if (command.equals("new") || command.equals("n")) {
                createNew(container, io);
            } else if (command.equals("browse") || command.equals("b")) {
                if (container.size() == 0) {
                    io.print("No bookmarks");
                } else {
                    browse(container, io);
                }
            } else if (command.equals("exit") || command.equals("e")) {
                break;
            } else if (command.equals("ser")) {
                testContainerSerialization(container, io);
            } else if (command.equals("samples") || command.equals("s")) {
                createSamples(container);
            } else {
                io.print("Invalid command");
            }
        }

        if (containerHasChanged(container)) {
            String save = io.nextLine("Save changes to file? yes/no");
            if (save.toLowerCase().equals("yes") || save.toLowerCase().equals("y")) {
                if (FileIO.saveContainerToFile(container, BOOKMARK_FILE)) {
                    io.print("Saved.");
                }
            } else {
                io.print("Quitting without saving.");
            }
        }
    }

    public static void createSampleSaveFile(FileIO fio) {
        BookmarkContainer bmc = new BookmarkContainer();
        createSamples(bmc);
        fio.saveContainerToFile(bmc, BOOKMARK_FILE);
    }

    private static void createSamples(BookmarkContainer container) {
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

    private static void browse(BookmarkContainer container, IO io) {
        if (container.getIndex() != 0) {
            String resume = io.nextLine("Type \"first\" to start from the beginning (last added) or leave empty to continue form your last browsed bookmark.");
            if (resume.equals("first")) container.setIndex(0);
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
                edit(container.getCurrent(), io);
            } else if (command.equals("ser")) {
                testBookmarkSerialization(container.getCurrent(), io);
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
        BookmarkContainer searchResult = new BookmarkContainer(container.searchByTagsOR(tags));
        if (searchResult.size() == 0) {
            io.print("No bookmarks matching the search criteria.");
        } else {
            browseList(new BookmarkContainer(container.searchByTagsOR(tags)), io);
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

    // List editing unfinished: User can only replace or remove all elements of
    // a list field with one value.
    private static void edit(Bookmark bm, IO io) {
        List<String> allFields = bm.getFieldNames();
        String field = io.nextLine("Type which field to edit ("
                + allFields.stream().collect(Collectors.joining(", "))
                + ") or \"exit\" to stop editing.");

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

    private static void editSingleField(Bookmark bm, String field, IO io) {
        String newEntry = io.nextLine("Current "
                + field + ": "
                + bm.getSingleField(field)
                + ". Set a new " + field
                + " or type the current " + field + " to remove it.");

        if (newEntry.equals(bm.getSingleField(field))) {
            bm.setSingleField(field, "");
            io.print(field + " removed.");
        } else if (newEntry.trim().equals("")) {
            io.print("No change made.");
        } else {
            bm.setSingleField(field, newEntry);
            io.print("New "
                    + field
                    + " set.");
        }
    }

//    private static void editListField(Bookmark bm, String field, IO io) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private static void addOrRemove(List<String> list, String item, String field, IO io) {
//        if (list.contains(item)) {
//            for (Iterator<String> iter = list.listIterator(); iter.hasNext();) {
//                String a = iter.next();
//                if (a.equals(item)) {
//                    iter.remove();
//                    io.print(field + " removed.");
//                }
//            }
//        } else if (!item.trim().equals("")) {
//            list.add(item);
//            io.print("New " + field.toLowerCase() + " added.");
//        } else {
//            io.print("No change made.");
//        }
//    }
//
    private static void createNew(BookmarkContainer container, IO io) {
        io.print("Provide the information, please (do not enter any text if you wish to leave the field blank)");
        Bookmark newB = Bookmark.createBookmark();
        for (String fieldName : newB.getFieldNames()) {
            if (!fieldName.equals("Added on")) {
              setFieldByUserInput(newB, fieldName, true, io);  
            }
        }
        newB.setAddedOn();
        container.add(newB);
        io.print("Bookmark created.");
    }

    private static void setFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        if (bm.fieldIsSingle(fieldName)) {
            setSingleFieldByUserInput(bm, fieldName, initial, io);
        } else {
            setListFieldByUserInput(bm, fieldName, initial, io);
        }
    }

    private static void setSingleFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        String prompt = initial ? fieldName + ":" : "new " + fieldName + ":";
        String newValue = io.nextLine(prompt);
        bm.setSingleField(fieldName, newValue);
    }

    private static void setListFieldByUserInput(Bookmark bm, String fieldName, boolean initial, IO io) {
        String prompt
                = "Give "
                + fieldName
                + " one by one for as long as you want; input an empty line to stop.";
        List<String> items = addList(io, prompt);
        bm.setListField(fieldName, items);
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

    private static void testBookmarkSerialization(Bookmark bm, IO io) {
        String json = Bookmark.serializeBookmark(bm);
        io.print("The bookmark in JSON:");
        io.print(json);

        Bookmark deserialized = Bookmark.deserializeBookmark(json);
        io.print("The bookmark deserialized from JSON:");
        io.print(deserialized.toString());
    }

    private static void testContainerSerialization(BookmarkContainer container, IO io) {
        String json = BookmarkContainer.serializeBookmarkContainer(container);
        io.print("The container in JSON:");
        io.print(json);

        BookmarkContainer deserialized = BookmarkContainer.deserializeBookmarkContainer(json);
        io.print("The container deserialized from JSON:");
        io.print(deserialized.toString());
    }

    private static boolean containerHasChanged(BookmarkContainer container) {
        BookmarkContainer savedContainer = FileIO.loadContainerFromFile(BOOKMARK_FILE);
        if (treatFileAsMissing || savedContainer == null) {
            return container.size() != 0;
        }
//        // Setting the index of the comparison container to 0 to omit comparing the index.
//        BookmarkContainer newContainer = container;
//        newContainer.setIndex(0);
        String newContainerJSON = BookmarkContainer.serializeBookmarkContainer(container);
        String savedContainerJSON = BookmarkContainer.serializeBookmarkContainer(savedContainer);
        return !savedContainerJSON.equals(newContainerJSON);
    }
}
