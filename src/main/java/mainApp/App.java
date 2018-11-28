package mainApp;

import IO.*;
import bookmark.BookmarkContainer;
import bookmark.Bookmark;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static final String BOOKMARK_FILE = "saved_bookmarks.txt";
    private static boolean changes = false;

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        run(io, true);
    }

    public static void run(IO io, boolean loadBookmarks) {
        // samples option for cucumber testing
        BookmarkContainer container;
        FileIO fio = new FileIO();
        if (loadBookmarks && Files.exists(Paths.get(BOOKMARK_FILE))) {
            container = fio.loadContainerFromFile(BOOKMARK_FILE);
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
        
        if (changes) {
            String save = io.nextLine("Save changes to file? yes/no");
            if (save.toLowerCase().equals("yes") || save.toLowerCase().equals("y")) {
                if (fio.saveContainerToFile(container, BOOKMARK_FILE)) {
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
        Bookmark routerBook = Bookmark.createBook("Reitittimet 1992-1996", "Koodi Kalevi", "43289-23432");
        routerBook.addToField("tags", "guide");
        container.add(routerBook);

        Bookmark fishBook = Bookmark.createBook("Kalaopas", "Kimmo Kala", "8493-33");
        fishBook.addToField("tags", "hobbies");
        fishBook.addToField("tags", "fishy");
        fishBook.addToField("tags", "guide");
        container.add(fishBook);
        
        changes = true;
    }

    private static void browse(BookmarkContainer container, IO io) {
        while (true) {
            io.print(container.getCurrent().getStringField("title") + " " + (container.getIndex() + 1) + "/" + container.size());
            String command = io.nextLine("Type \"next\" to see the next bookmark, \"prev\" to see the previous bookmark, \"show\" to show more information on the current one, \"search\" to search for bookmarks, \"edit\" to edit the current one or \"exit\" to stop browsing bookmarks.");
            if (command.equals("next")) {
                container.getNext();
            } else if (command.equals("prev")) {
                container.getPrevious();
            } else if (command.equals("show")) {
                io.print(container.getCurrent().toString());
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
            io.print(container.getCurrent().getStringField("title") + " " + (container.getIndex() + 1) + "/" + container.size());
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

        if (!allFields.contains(field)) {
            io.print("Invalid field.");
            edit(bm, io);
        }

        if (bm.fieldIsString(field)) {
            editStringField(bm, field, io);
        } else {
            //editListField(bm, field, io);
        }

    }

    private static void editStringField(Bookmark bm, String field, IO io) {
        String newEntry = io.nextLine("Current "
                + field + ": "
                + bm.getStringField(field)
                + ". Set a new " + field
                + " or type the current " + field + " to remove it.");

        if (newEntry.equals(bm.getStringField(field))) {
            bm.setField(field, "");
            io.print(field + " removed.");
        } else if (newEntry.trim().equals("")) {
            io.print("No change made.");
        } else {
            bm.setField(field, newEntry);
            io.print("New "
                    + field
                    + " set.");
        }
        changes = true;
    }

//    private static void editListField(Bookmark bm, String field, IO io) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    Deprecated implementation of editing
//    private static void edit(AbstractBookmark bm, IO io) {
//        String fields = setFields(bm);
//
//        while (true) {
//            String field = io.nextLine("Type which field to edit (" + fields + ") or \"exit\" to stop editing.");
//                
//            if (field.equals("title")) {
//                String title = io.nextLine("Current title: " + bm.getTitle() + ". Set a new title or type the current title to remove it.");
//                if (title.equals(bm.getTitle())) {
//                    bm.setTitle("");
//                    io.print("Title removed.");
//                } else if (!title.trim().equals("")) {
//                    bm.setTitle(title);
//                    io.print("New title set.");
//                } else {
//                    io.print("No change made.");
//                }
//            } else if (field.equals("comment")) {
//                String comment = io.nextLine("Current comment: " + bm.getComment() + ". Set a new comment or type the current comment to remove it.");
//                if (comment.equals(bm.getComment())) {
//                    bm.setComment("");
//                    io.print("Comment removed.");
//                } else if (!comment.trim().equals("")) {
//                    bm.setComment(comment);
//                    io.print("New comment set.");
//                } else {
//                    io.print("No change made.");
//                }
//            } else if (field.equals("tags")) {
//                List<String> tags = bm.getTags();
//                io.print(AbstractBookmark.attributeToString("Tags: ", AbstractBookmark.list(tags)));
//                String tag = io.nextLine("Type an existing tag to remove it or a new one to add it.");
//                addOrRemove(tags, tag, "Tag", io);
//            } else if (field.equals("prerequisites")) {
//                List<String> preC = bm.getPrerequisiteCourses();
//                io.print(AbstractBookmark.attributeToString("Prerequisite courses: ", AbstractBookmark.list(preC)));
//                String course = io.nextLine("Type an existing course to remove it or a new one to add it.");
//                addOrRemove(preC, course, "Course", io);               
//            } else if (field.equals("related")) {
//                List<String> relC = bm.getRelatedCourses();
//                io.print(AbstractBookmark.attributeToString("Related courses: ", AbstractBookmark.list(relC)));
//                String course = io.nextLine("Type an existing course to remove it or a new one to add it.");
//                addOrRemove(relC, course, "Course", io);
//            } else if(field.equals("exit")) {
//                break;
//            } else if (bm.getType().equals("Book")) {
////                editBook(field, io, bm);
//            } else {
//                io.print("Invalid field.");
//            }
//        }
//    }
//
//    private static String setFields(AbstractBookmark bm) {
//        String fields = "title, tags, prerequisites, related, comment";
//        if (bm.getType().equals("Book")) {
//            fields = "author, isbn, " + fields;
//        }
//        return fields;
//    }
//    private static void editBook(String field, IO io, AbstractBookmark bm) {
//        if (field.equals("author")) {
//            String author = io.nextLine("Current author: " + ((Book) bm).getAuthor() + ". Set a new author or type the current author to remove it.");
//            if (author.equals(((Book)bm).getAuthor())) {
//                ((Book)bm).setAuthor("");
//                io.print("Author removed.");
//            } else if (!author.trim().equals("")) {
//                ((Book)bm).setAuthor(author);
//                io.print("New author set.");
//            } else {
//                io.print("No change made.");
//            }
//        } else if (field.equals("isbn")) {
//            String isbn = io.nextLine("Current author: " + ((Book) bm).getIsbn() + ". Set a new ISBN or type the current ISBN to remove it.");
//            if (isbn.equals(((Book)bm).getIsbn())) {
//                ((Book)bm).setIsbn("");
//                io.print("ISBN removed.");
//            } else if (!isbn.trim().equals("")) {
//                ((Book)bm).setIsbn(isbn);
//                io.print("New ISBN set.");
//            } else {
//                io.print("No change made.");
//            }
//        }
//    }
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
        newB.setField("title", io.nextLine("Title:"));
        newB.setField("author", io.nextLine("Author:"));
        newB.setField("url", io.nextLine("Url:"));
        newB.setField("description", io.nextLine("Description:"));
        newB.setField("isbn", io.nextLine("ISBN:"));  
        newB.setField("tags", addList(io, "Give tags one by one for as long as you want; input an empty line to stop."));
        newB.setField("comment", io.nextLine("Type a comment if you want to leave one."));
        newB.setField("prerequisite courses", addList(io, "Give as many prerequisite courses as you want."));
        newB.setField("related courses", addList(io, "Give as many related courses as you want."));
        container.add(newB);
        io.print("Bookmark created.");
        changes = true;
    }
    
    private static ArrayList<String> addList(IO io, String printLine) {
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String newO = io.nextLine(printLine);
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
}
